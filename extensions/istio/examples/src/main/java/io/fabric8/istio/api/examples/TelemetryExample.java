/**
 * Copyright (C) 2015 Red Hat, Inc.
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
package io.fabric8.istio.api.examples;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.istio.api.telemetry.v1alpha1.TelemetryBuilder;
import io.fabric8.istio.api.telemetry.v1alpha1.TelemetryList;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.telemetry.v1alpha1.TracingBuilder;
import io.fabric8.istio.internal.api.type.v1beta1.WorkloadSelectorBuilder;

public class TelemetryExample {
  private static final String NAMESPACE = "test";
  private static final Logger logger = LoggerFactory.getLogger(TelemetryExample.class.getSimpleName());

  public static void main(String[] args) {
    IstioClient client = ClientFactory.newClient(args);

    System.out.println("Creating a Telemetry");
    // Example from: https://istio.io/latest/docs/tasks/observability/distributed-tracing/configurability/telemetry-api/
    client.telemetries().inNamespace(NAMESPACE).create(new TelemetryBuilder()
      .withNewMetadata()
      .withName("workload-override")
      .endMetadata()
      .withNewInternalSpec()
        .withSelector(new WorkloadSelectorBuilder().withMatchLabels(Collections.singletonMap("service.istio.io/canonical-name", "frontend")).build())
        .withTracing(new TracingBuilder().withDisableSpanReporting(true).build())
      .endInternalSpec()
      .build());

    logger.info("Listing Telemetry instances:");
    TelemetryList list = client.telemetries().inNamespace(NAMESPACE).list();
    list.getItems().forEach(b -> logger.info(b.getMetadata().getName()));
    logger.info("Done");
  }
}
