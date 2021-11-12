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

import io.fabric8.istio.api.telemetry.v1alpha1.Telemetry;
import io.fabric8.istio.api.telemetry.v1alpha1.TelemetryBuilder;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.telemetry.v1alpha1.TracingBuilder;
import io.fabric8.istio.internal.api.type.v1beta1.WorkloadSelectorBuilder;
import io.fabric8.istio.mock.EnableIstioMockClient;
import io.fabric8.istio.mock.IstioMockServer;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import okhttp3.mockwebserver.RecordedRequest;

@EnableIstioMockClient
class TelemetryTest {

  IstioClient client;
  IstioMockServer server;

  @Test
  @DisplayName("Should get a Telemetry")
  void testGet() {
    Telemetry service2 = new TelemetryBuilder().withNewMetadata().withName("service2").endMetadata().build();
    server.expect().get().withPath("/apis/telemetry.istio.io/v1alpha1/namespaces/ns2/telemetries/service2")
      .andReturn(HttpURLConnection.HTTP_OK, service2)
      .once();

    Telemetry service = client.telemetries().inNamespace("ns2").withName("service2").get();
    assertNotNull(service);
    assertEquals("service2", service.getMetadata().getName());
  }

  @Test
  @DisplayName("Should Create a Telemetry")
  void testCreate() throws InterruptedException {
    // Example from: https://istio.io/latest/docs/tasks/observability/distributed-tracing/configurability/telemetry-api/
    Telemetry service = new TelemetryBuilder()
      .withNewMetadata()
      .withName("workload-override")
      .endMetadata()
      .withNewInternalSpec()
      .withSelector(new WorkloadSelectorBuilder().withMatchLabels(
        Collections.singletonMap("service.istio.io/canonical-name", "frontend")).build())
      .withTracing(new TracingBuilder().withDisableSpanReporting(true).build())
      .endInternalSpec()
      .build();

    server.expect().post().withPath("/apis/telemetry.istio.io/v1alpha1/namespaces/ns2/telemetries")
      .andReturn(HttpURLConnection.HTTP_OK, service)
      .once();
    service = client.telemetries().inNamespace("ns2").create(service);
    assertNotNull(service);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"telemetry.istio.io/v1alpha1\","
        + "\"kind\":\"Telemetry\","
        + "\"metadata\":{\"name\":\"workload-override\"},"
        + "\"spec\":{"
        +   "\"selector\":{\"match_labels\":{\"service.istio.io/canonical-name\":\"frontend\"}},"
        +   "\"tracing\":[{\"disable_span_reporting\":true}]}}",
      recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should Delete a Telemetry")
  void testDelete() throws InterruptedException {
    server.expect().delete().withPath("/apis/telemetry.istio.io/v1alpha1/namespaces/ns3/telemetries/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new TelemetryBuilder().build())
      .once();
    Boolean deleted = client.telemetries().inNamespace("ns3").withName("service3").delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Background\"}", recordedRequest.getBody().readUtf8());
  }

  @Test
  @DisplayName("Should delete with PropagationPolicy=Orphan")
  void testDeleteOrphan() throws InterruptedException {
    server.expect().delete().withPath("/apis/telemetry.istio.io/v1alpha1/namespaces/ns3/telemetries/service3")
      .andReturn(HttpURLConnection.HTTP_OK, new TelemetryBuilder().build())
      .once();
    Boolean deleted = client.telemetries().inNamespace("ns3").withName("service3").withPropagationPolicy(DeletionPropagation.ORPHAN).delete();
    assertTrue(deleted);

    RecordedRequest recordedRequest = server.takeRequest();
    assertEquals("{\"apiVersion\":\"v1\",\"kind\":\"DeleteOptions\",\"propagationPolicy\":\"Orphan\"}", recordedRequest.getBody().readUtf8());
  }
}
