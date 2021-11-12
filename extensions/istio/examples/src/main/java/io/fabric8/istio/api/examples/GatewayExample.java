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

import io.fabric8.istio.api.networking.v1beta1.GatewayBuilder;
import io.fabric8.istio.api.networking.v1beta1.GatewayList;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.networking.v1beta1.PortBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.ServerBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.ServerTLSSettingsBuilder;

public class GatewayExample {
  private static final String NAMESPACE = "test";
  private static final Logger logger = LoggerFactory.getLogger(GatewayExample.class.getSimpleName());

  public static void main(String[] args) {
    IstioClient client = ClientFactory.newClient(args);

    System.out.println("Creating a gateway");
    // Example from: https://istio.io/latest/docs/reference/config/networking/gateway/
    client.gateways().inNamespace(NAMESPACE).create(new GatewayBuilder()
        .withNewMetadata()
          .withName("my-gateway")
          .withNamespace("some-config-namespace")
        .endMetadata()
        .withNewInternalSpec()
        .withSelector(Collections.singletonMap("app", "my-gateway-controller"))
        .withServers(new ServerBuilder()
          .withPort(new PortBuilder().withNumber(80).withProtocol("HTTP").withName("http").build())
          .withHosts("uk.bookinfo.com", "eu.bookinfo.com")
          .withTls(new ServerTLSSettingsBuilder().withHttpsRedirect(true).build())
          .build())
        .endInternalSpec()
      .build());

    logger.info("Listing gateway instances:");
    GatewayList list = client.gateways().inNamespace(NAMESPACE).list();
    list.getItems().forEach(b -> logger.info(b.getMetadata().getName()));
    logger.info("Done");
  }
}
