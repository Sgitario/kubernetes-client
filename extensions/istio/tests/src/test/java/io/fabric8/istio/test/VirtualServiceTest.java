/**
 * Copyright (C) 2021 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.istio.test;

import io.fabric8.istio.api.networking.v1beta1.VirtualService;
import io.fabric8.istio.api.networking.v1beta1.VirtualServiceBuilder;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.networking.v1beta1.Destination;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPMatchRequestBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRewriteBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRouteBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRouteDestinationBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.StringMatch;
import io.fabric8.istio.internal.api.networking.v1beta1.StringMatchBuilder;
import io.fabric8.istio.mock.EnableIstioMockClient;
import io.fabric8.istio.mock.IstioMockServer;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;

@EnableIstioMockClient
class VirtualServiceTest {

  IstioClient client;
  IstioMockServer server;

  @Test
  @DisplayName("Should get a Virtual Service")
  void testGet() {
    VirtualService service2 = new VirtualServiceBuilder().withNewMetadata().withName("service2").endMetadata().build();
    server.expect().get().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns2/virtualservices/service2")
      .andReturn(HttpURLConnection.HTTP_OK, service2)
      .once();

    VirtualService service = client.virtualServices().inNamespace("ns2").withName("service2").get();
    assertNotNull(service);
    assertEquals("service2", service.getMetadata().getName());
  }

  @Test
  @DisplayName("Should Create a Virtual Service")
  void testCreate() throws InterruptedException {
    // Example from: https://istio.io/latest/docs/reference/config/networking/virtual-service/
    VirtualService service = new VirtualServiceBuilder()
      .withNewMetadata()
      .withName("reviews-route")
      .endMetadata()
      .withNewInternalSpec()
      .withHosts("reviews-v2-routes")
      .withHttp(
        new HTTPRouteBuilder().withName("reviews-v2-routes")
          .withMatch(new HTTPMatchRequestBuilder().withUri(new StringMatchBuilder().withPrefix("/wpcatalog").build()).build(),
            new HTTPMatchRequestBuilder().withUri(StringMatch.prefix("/consumercatalog")).build())
          .withRewrite(new HTTPRewriteBuilder().withUri("/newcatalog").build())
          .withRoute(new HTTPRouteDestinationBuilder().withDestination(new Destination("reviews.prod.svc.cluster.local", null, "v2")).build())
          .build()
      )
      .endInternalSpec()
      .build();

    server.expect().post().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns2/virtualservices")
      .andReturn(HttpURLConnection.HTTP_OK, service)
      .once();
    service = client.virtualServices().inNamespace("ns2").create(service);
    assertNotNull(service);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"networking.istio.io/v1beta1\","
        + "\"kind\":\"VirtualService\","
        + "\"metadata\":{\"name\":\"reviews-route\"},"
        + "\"spec\":{"
        +   "\"hosts\":[\"reviews-v2-routes\"],"
        +   "\"http\":[{"
        +     "\"match\":["
        +       "{\"uri\":{\"prefix\":\"/wpcatalog\"}},"
        +       "{\"uri\":{\"prefix\":\"/consumercatalog\"}}],"
        +     "\"name\":\"reviews-v2-routes\","
        +     "\"rewrite\":{\"uri\":\"/newcatalog\"},"
        +     "\"route\":[{\"destination\":{\"host\":\"reviews.prod.svc.cluster.local\",\"subset\":\"v2\"}}]}]}}",
      recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should Delete a Virtual Service")
  void testDelete() throws InterruptedException {
    server.expect().delete().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns3/virtualservices/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new VirtualServiceBuilder().build())
      .once();
    Boolean deleted = client.virtualServices().inNamespace("ns3").withName("service3").delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Background\"}", recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should delete with PropagationPolicy=Orphan")
  void testDeleteOrphan() throws InterruptedException {
    server.expect().delete().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns3/virtualservices/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new VirtualServiceBuilder().build())
      .once();
    Boolean deleted = client.virtualServices().inNamespace("ns3").withName("service3").withPropagationPolicy(DeletionPropagation.ORPHAN).delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Orphan\"}", recordedRequest.getBody().readUtf8());
  }
}
