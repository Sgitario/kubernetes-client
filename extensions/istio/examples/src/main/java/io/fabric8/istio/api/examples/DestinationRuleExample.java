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

import io.fabric8.istio.api.networking.v1beta1.DestinationRuleBuilder;
import io.fabric8.istio.api.networking.v1beta1.DestinationRuleList;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettingsBuilder;
import io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettings_SimpleBuilder;

public class DestinationRuleExample {
  private static final String NAMESPACE = "test";
  private static final Logger logger = LoggerFactory.getLogger(DestinationRuleExample.class.getSimpleName());

  public static void main(String[] args) {
    IstioClient client = ClientFactory.newClient(args);

    System.out.println("Creating a destination rule");
    // Example from: https://istio.io/latest/docs/reference/config/networking/destination-rule/
    client.destinationRules().inNamespace(NAMESPACE).create(new DestinationRuleBuilder()
      .withNewMetadata()
      .withName("reviews-route")
      .endMetadata()
      .withNewInternalSpec()
      .withHost("ratings.prod.svc.cluster.local")
      .withNewTrafficPolicy()
      .withLoadBalancer(
        new LoadBalancerSettingsBuilder().withLbPolicy(new LoadBalancerSettings_SimpleBuilder().withSimple(2).build()).build())
        .endTrafficPolicy()
        .endInternalSpec()
      .build());

    logger.info("Listing destination rules instances:");
    DestinationRuleList list = client.destinationRules().inNamespace(NAMESPACE).list();
    list.getItems().forEach(b -> logger.info(b.getMetadata().getName()));
    logger.info("Done");
  }
}
