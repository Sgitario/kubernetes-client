package io.fabric8.istio.client;

import io.fabric8.istio.api.networking.v1beta1.DestinationRule;
import io.fabric8.istio.api.networking.v1beta1.DestinationRuleList;
import io.fabric8.istio.api.networking.v1beta1.Gateway;
import io.fabric8.istio.api.networking.v1beta1.GatewayList;
import io.fabric8.istio.api.networking.v1beta1.ServiceEntry;
import io.fabric8.istio.api.networking.v1beta1.ServiceEntryList;
import io.fabric8.istio.api.networking.v1beta1.Sidecar;
import io.fabric8.istio.api.networking.v1beta1.SidecarList;
import io.fabric8.istio.api.networking.v1beta1.VirtualService;
import io.fabric8.istio.api.networking.v1beta1.VirtualServiceList;
import io.fabric8.istio.api.networking.v1beta1.WorkloadEntry;
import io.fabric8.istio.api.networking.v1beta1.WorkloadEntryList;
import io.fabric8.istio.api.security.v1beta1.AuthorizationPolicy;
import io.fabric8.istio.api.security.v1beta1.AuthorizationPolicyList;
import io.fabric8.istio.api.security.v1beta1.PeerAuthentication;
import io.fabric8.istio.api.security.v1beta1.PeerAuthenticationList;
import io.fabric8.istio.api.security.v1beta1.RequestAuthentication;
import io.fabric8.istio.api.security.v1beta1.RequestAuthenticationList;
import io.fabric8.kubernetes.client.BaseClient;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.Handlers;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.WithRequestCallable;
import io.fabric8.kubernetes.client.dsl.FunctionCallable;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import okhttp3.OkHttpClient;

public class DefaultIstioClient extends BaseClient implements NamespacedIstioClient {

  public DefaultIstioClient() {
    super();
  }

  public DefaultIstioClient(Config configuration) {
    super(configuration);
  }

  public DefaultIstioClient(OkHttpClient httpClient, Config configuration) {
    super(httpClient, configuration);
  }

  @Override
  public NamespacedIstioClient inAnyNamespace() {
    return inNamespace(null);
  }

  @Override
  public NamespacedIstioClient inNamespace(String namespace) {
    Config updated = new ConfigBuilder(getConfiguration())
      .withNamespace(namespace)
      .build();

    return new DefaultIstioClient(getHttpClient(), updated);
  }

  @Override
  public FunctionCallable<NamespacedIstioClient> withRequestConfig(RequestConfig requestConfig) {
    return new WithRequestCallable<>(this, requestConfig);
  }

  // networking

  @Override
  public MixedOperation<DestinationRule, DestinationRuleList, Resource<DestinationRule>> destinationRules() {
    return Handlers.getOperation(DestinationRule.class, DestinationRuleList.class, this.getHttpClient(),
      this.getConfiguration());
  }

  @Override
  public MixedOperation<Gateway, GatewayList, Resource<Gateway>> gateways() {
    return Handlers.getOperation(Gateway.class, GatewayList.class, this.getHttpClient(), this.getConfiguration());
  }

  @Override
  public MixedOperation<ServiceEntry, ServiceEntryList, Resource<ServiceEntry>> serviceEntries() {
    return Handlers.getOperation(ServiceEntry.class, ServiceEntryList.class, this.getHttpClient(), this.getConfiguration());
  }

  @Override
  public MixedOperation<Sidecar, SidecarList, Resource<Sidecar>> sidecars() {
    return Handlers.getOperation(Sidecar.class, SidecarList.class, this.getHttpClient(), this.getConfiguration());
  }

  @Override
  public MixedOperation<VirtualService, VirtualServiceList, Resource<VirtualService>> virtualServices() {
    return Handlers.getOperation(VirtualService.class, VirtualServiceList.class, this.getHttpClient(), this.getConfiguration());
  }

  @Override
  public MixedOperation<WorkloadEntry, WorkloadEntryList, Resource<WorkloadEntry>> workloadEntries() {
    return Handlers.getOperation(WorkloadEntry.class, WorkloadEntryList.class, this.getHttpClient(), this.getConfiguration());
  }

  // security
  @Override
  public MixedOperation<PeerAuthentication, PeerAuthenticationList, Resource<PeerAuthentication>> peerAuthentications() {
    return Handlers.getOperation(PeerAuthentication.class, PeerAuthenticationList.class, this.getHttpClient(),
      this.getConfiguration());
  }

  @Override
  public MixedOperation<RequestAuthentication, RequestAuthenticationList, Resource<RequestAuthentication>> requestAuthentications() {
    return Handlers.getOperation(RequestAuthentication.class, RequestAuthenticationList.class, this.getHttpClient(),
      this.getConfiguration());
  }

  public MixedOperation<AuthorizationPolicy, AuthorizationPolicyList, Resource<AuthorizationPolicy>> authorizationPolicies() {
    return Handlers.getOperation(AuthorizationPolicy.class, AuthorizationPolicyList.class, this.getHttpClient(),
      this.getConfiguration());
  }

}
