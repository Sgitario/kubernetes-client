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

import io.fabric8.istio.api.security.v1beta1.PeerAuthenticationBuilder;
import io.fabric8.istio.api.security.v1beta1.PeerAuthenticationList;
import io.fabric8.istio.client.IstioClient;
import io.fabric8.istio.internal.api.security.v1beta1.PeerAuthenticationMutualTLS;
import io.fabric8.istio.internal.api.security.v1beta1.PeerAuthenticationMutualTLSMode;
import io.fabric8.istio.internal.api.type.v1beta1.WorkloadSelectorBuilder;

public class PeerAuthenticationExample {
  private static final String NAMESPACE = "test";
  private static final Logger logger = LoggerFactory.getLogger(PeerAuthenticationExample.class.getSimpleName());

  public static void main(String[] args) {
    IstioClient client = ClientFactory.newClient(args);

    System.out.println("Creating a PeerAuthentication entry");
    client.peerAuthentications().inNamespace(NAMESPACE).create(new PeerAuthenticationBuilder()
      .withNewMetadata()
      .withName("details-svc")
      .endMetadata()
      .withNewInternalSpec()
      .withSelector(new WorkloadSelectorBuilder().addToMatchLabels("app", "reviews").build())
      .withMtls(new PeerAuthenticationMutualTLS(PeerAuthenticationMutualTLSMode.DISABLE))
      .endInternalSpec()
      .build());

    logger.info("Listing workload entry instances:");
    PeerAuthenticationList list = client.peerAuthentications().inNamespace(NAMESPACE).list();
    list.getItems().forEach(b -> logger.info(b.getMetadata().getName()));
    logger.info("Done");
  }
}
