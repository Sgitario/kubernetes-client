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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.fabric8.istio.api.networking.v1beta1.DestinationRule;
import io.fabric8.istio.api.networking.v1beta1.DestinationRuleBuilder;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettingsBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettingsConsistentHash;
import io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettingsConsistentHashLBBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettingsConsistentHashLBHttpHeaderName;
import io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettingsSimple;
import io.fabric8.istio.mock.EnableIstioMockClient;
import io.fabric8.istio.mock.IstioMockServer;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import okhttp3.mockwebserver.RecordedRequest;

@EnableIstioMockClient
class DestinationRuleTest {

  IstioClient client;
  IstioMockServer server;

  @Test
  @DisplayName("Should get a Destination Rule")
  void testGet() {
    DestinationRule service2 = new DestinationRuleBuilder().withNewMetadata().withName("service2").endMetadata().build();
    server.expect().get().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns2/destinationrules/service2")
      .andReturn(HttpURLConnection.HTTP_OK, service2)
      .once();

    DestinationRule service = client.destinationRules().inNamespace("ns2").withName("service2").get();
    assertNotNull(service);
    assertEquals("service2", service.getMetadata().getName());
  }

  @Test
  @DisplayName("Should Create a Destination Rule with simple settings")
  void testCreateWithSimpleSettings() throws InterruptedException {
    // Example from: https://istio.io/latest/docs/reference/config/networking/destination-rule/
    DestinationRule service = new DestinationRuleBuilder()
      .withNewMetadata()
      .withName("reviews-route")
      .endMetadata()
      .withNewInternalSpec()
      .withHost("ratings.prod.svc.cluster.local")
      .withNewTrafficPolicy()
      .withLoadBalancer(
        new LoadBalancerSettingsBuilder().withLbPolicy(new LoadBalancerSettingsSimple(2)).build())
      .endTrafficPolicy()
      .endInternalSpec()
      .build();

    server.expect().post().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns2/destinationrules")
      .andReturn(HttpURLConnection.HTTP_OK, service)
      .once();
    service = client.destinationRules().inNamespace("ns2").create(service);
    assertNotNull(service);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"networking.istio.io/v1beta1\","
        + "\"kind\":\"DestinationRule\","
        + "\"metadata\":{\"name\":\"reviews-route\"},"
        + "\"spec\":{"
        + "\"host\":\"ratings.prod.svc.cluster.local\","
        + "\"traffic_policy\":{\"load_balancer\":{\"simple\":2}}}}",
      recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should Create a Destination Rule with consistent hash settings")
  void testCreateWithConsistentHashSettings() throws InterruptedException {
    // Example from: https://istio.io/latest/docs/reference/config/networking/destination-rule/
    DestinationRule service = new DestinationRuleBuilder()
      .withNewMetadata()
      .withName("reviews-route")
      .endMetadata()
      .withNewInternalSpec()
      .withHost("ratings.prod.svc.cluster.local")
      .withNewTrafficPolicy()
      .withLoadBalancer(
        new LoadBalancerSettingsBuilder().withLbPolicy(
          new LoadBalancerSettingsConsistentHash(new LoadBalancerSettingsConsistentHashLBBuilder().withHashKey(
            new LoadBalancerSettingsConsistentHashLBHttpHeaderName("x-user")).build())).build())
      .endTrafficPolicy()
      .endInternalSpec()
      .build();

    server.expect().post().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns2/destinationrules")
      .andReturn(HttpURLConnection.HTTP_OK, service)
      .once();
    service = client.destinationRules().inNamespace("ns2").create(service);
    assertNotNull(service);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"networking.istio.io/v1beta1\","
        + "\"kind\":\"DestinationRule\","
        + "\"metadata\":{\"name\":\"reviews-route\"},"
        + "\"spec\":{"
        + "\"host\":\"ratings.prod.svc.cluster.local\","
        + "\"traffic_policy\":{\"load_balancer\":{\"consistent_hash\":{\"http_header_name\":\"x-user\"}}}}}",
      recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should Delete a Destination Rule")
  void testDelete() throws InterruptedException {
    server.expect().delete().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns3/destinationrules/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new DestinationRuleBuilder().build())
      .once();
    Boolean deleted = client.destinationRules().inNamespace("ns3").withName("service3").delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Background\"}", recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should delete with PropagationPolicy=Orphan")
  void testDeleteOrphan() throws InterruptedException {
    server.expect().delete().withPath("/apis/networking.istio.io/v1beta1/namespaces/ns3/destinationrules/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new DestinationRuleBuilder().build())
      .once();
    Boolean deleted = client.destinationRules().inNamespace("ns3").withName("service3").withPropagationPolicy(DeletionPropagation.ORPHAN).delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Orphan\"}", recordedRequest.getBody().readUtf8());
  }
}
