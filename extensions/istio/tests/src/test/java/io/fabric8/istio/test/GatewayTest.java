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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.HttpURLConnection;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.fabric8.istio.api.networking.v1beta1.Gateway;
import io.fabric8.istio.api.networking.v1beta1.GatewayBuilder;
import io.fabric8.istio.api.networking.v1beta1.VirtualService;
import io.fabric8.istio.api.networking.v1beta1.VirtualServiceBuilder;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.networking.v1beta1.Destination;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPMatchRequestBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRewriteBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRouteBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRouteDestinationBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.PortBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.ServerBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.ServerTLSSettingsBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.StringMatch;
import io.fabric8.istio.internal.api.networking.v1beta1.StringMatchBuilder;
import io.fabric8.istio.mock.EnableIstioMockClient;
import io.fabric8.istio.mock.IstioMockServer;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import okhttp3.mockwebserver.RecordedRequest;

@EnableIstioMockClient
class GatewayTest {

  IstioClient client;
  IstioMockServer server;

  @Test
  @DisplayName("Should get a Gateway")
  void testGet() {
    Gateway service2 = new GatewayBuilder().withNewMetadata().withName("service2").endMetadata().build();
    server.expect().get().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns2/gateways/service2")
      .andReturn(HttpURLConnection.HTTP_OK, service2)
      .once();

    Gateway service = client.gateways().inNamespace("ns2").withName("service2").get();
    assertNotNull(service);
    assertEquals("service2", service.getMetadata().getName());
  }

  @Test
  @DisplayName("Should Create a Gateway")
  void testCreate() throws InterruptedException {
    // Example from: https://istio.io/latest/docs/reference/config/networking/virtual-service/
    Gateway service = new GatewayBuilder()
      .withNewMetadata()
      .withName("my-gateway")
      .endMetadata()
      .withNewInternalSpec()
      .withSelector(Collections.singletonMap("app", "my-gateway-controller"))
      .withServers(new ServerBuilder()
        .withPort(new PortBuilder().withNumber(80).withProtocol("HTTP").withName("http").build())
        .withHosts("uk.bookinfo.com", "eu.bookinfo.com")
        .withTls(new ServerTLSSettingsBuilder().withHttpsRedirect(true).build())
        .build())
      .endInternalSpec()
      .build();

    server.expect().post().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns2/gateways")
      .andReturn(HttpURLConnection.HTTP_OK, service)
      .once();
    service = client.gateways().inNamespace("ns2").create(service);
    assertNotNull(service);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"networking.istio.io/v1beta1\","
        + "\"kind\":\"Gateway\","
        + "\"metadata\":{\"name\":\"my-gateway\"},"
        + "\"spec\":{"
        +   "\"selector\":{\"app\":\"my-gateway-controller\"},"
        +   "\"servers\":[{\"hosts\":[\"uk.bookinfo.com\",\"eu.bookinfo.com\"],"
        +   "\"port\":{\"name\":\"http\",\"number\":80,\"protocol\":\"HTTP\"},"
        +   "\"tls\":{\"https_redirect\":true}}]}}",
      recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should Delete a Gateway")
  void testDelete() throws InterruptedException {
    server.expect().delete().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns3/gateways/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new GatewayBuilder().build())
      .once();
    Boolean deleted = client.gateways().inNamespace("ns3").withName("service3").delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Background\"}", recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should delete with PropagationPolicy=Orphan")
  void testDeleteOrphan() throws InterruptedException {
    server.expect().delete().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns3/gateways/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new GatewayBuilder().build())
      .once();
    Boolean deleted = client.gateways().inNamespace("ns3").withName("service3").withPropagationPolicy(DeletionPropagation.ORPHAN).delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Orphan\"}", recordedRequest.getBody().readUtf8());
  }
}
