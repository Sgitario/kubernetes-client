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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.istio.api.networking.v1beta1.ServiceEntryBuilder;
import io.fabric8.istio.api.networking.v1beta1.ServiceEntryList;
import io.fabric8.istio.api.networking.v1beta1.VirtualServiceBuilder;
import io.fabric8.istio.api.networking.v1beta1.VirtualServiceList;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.networking.v1beta1.Destination;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPMatchRequestBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRewriteBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRouteBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.HTTPRouteDestinationBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.PortBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.ServiceEntryLocation;
import io.fabric8.istio.internal.api.networking.v1beta1.StringMatch;

public class ServiceEntryExample {
  private static final String NAMESPACE = "test";
  private static final Logger logger = LoggerFactory.getLogger(ServiceEntryExample.class.getSimpleName());

  public static void main(String[] args) {
    IstioClient client = ClientFactory.newClient(args);

    System.out.println("Creating a service entry");
    // Example from: https://istio.io/latest/docs/reference/config/networking/service-entry/
    client.serviceEntries().inNamespace(NAMESPACE).create(new ServiceEntryBuilder()
      .withNewMetadata()
      .withName("external-svc-https")
      .endMetadata()
      .withNewInternalSpec()
      .withHosts("api.dropboxapi.com", "www.googleapis.com")
      .withLocation(ServiceEntryLocation.MESH_EXTERNAL)
      .withPorts(new PortBuilder().withName("https").withProtocol("TLS").withNumber(443).build())
      .endInternalSpec()
      .build());

    logger.info("Listing Virtual Service Instances:");
    ServiceEntryList list = client.serviceEntries().inNamespace(NAMESPACE).list();
    list.getItems().forEach(b -> logger.info(b.getMetadata().getName()));
    logger.info("Done");
  }
}
