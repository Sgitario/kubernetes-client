package io.fabric8.istio.internal.api.networking.v1beta1;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.BuildableReference;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class LoadBalancerSettingsConsistentHash implements Serializable {

  @JsonProperty("http_header_name")
  private String httpHeaderName;

  @JsonProperty("http_cookie")
  private HTTPCookie httpCookie;

  @JsonProperty("use_source_ip")
  private Boolean useSourceIp;

  @JsonProperty("http_query_parameter_name")
  private Boolean httpQueryParameterName;

  public String getHttpHeaderName() {
    return httpHeaderName;
  }

  public void setHttpHeaderName(String httpHeaderName) {
    this.httpHeaderName = httpHeaderName;
  }

  public HTTPCookie getHttpCookie() {
    return httpCookie;
  }

  public void setHttpCookie(HTTPCookie httpCookie) {
    this.httpCookie = httpCookie;
  }

  public Boolean getUseSourceIp() {
    return useSourceIp;
  }

  public void setUseSourceIp(Boolean useSourceIp) {
    this.useSourceIp = useSourceIp;
  }

  public static final LoadBalancerSettingsConsistentHash httpHeaderName(String httpHeaderName) {
    LoadBalancerSettingsConsistentHash consistentHash = new LoadBalancerSettingsConsistentHash();
    consistentHash.httpHeaderName = httpHeaderName;
    return consistentHash;
  }

  public static final LoadBalancerSettingsConsistentHash httpCookie(HTTPCookie httpCookie) {
    LoadBalancerSettingsConsistentHash consistentHash = new LoadBalancerSettingsConsistentHash();
    consistentHash.httpCookie = httpCookie;
    return consistentHash;
  }

  public static final LoadBalancerSettingsConsistentHash useSourceIp(boolean useSourceIp) {
    LoadBalancerSettingsConsistentHash consistentHash = new LoadBalancerSettingsConsistentHash();
    consistentHash.useSourceIp = useSourceIp;
    return consistentHash;
  }

  public static final LoadBalancerSettingsConsistentHash httpQueryParameterName(boolean httpQueryParameterName) {
    LoadBalancerSettingsConsistentHash consistentHash = new LoadBalancerSettingsConsistentHash();
    consistentHash.httpQueryParameterName = httpQueryParameterName;
    return consistentHash;
  }
}
