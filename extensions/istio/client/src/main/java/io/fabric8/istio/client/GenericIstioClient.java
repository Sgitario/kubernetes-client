package io.fabric8.istio.client;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.AnyNamespaceable;
import io.fabric8.kubernetes.client.dsl.Namespaceable;
import io.fabric8.kubernetes.client.dsl.RequestConfigurable;

public interface GenericIstioClient<C extends Client>
  extends Client, IstioClient, Namespaceable<C>, AnyNamespaceable<C>, RequestConfigurable<C> {
}
