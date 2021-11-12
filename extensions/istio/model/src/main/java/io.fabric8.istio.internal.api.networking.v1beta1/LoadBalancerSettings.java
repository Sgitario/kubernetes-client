package io.fabric8.istio.internal.api.networking.v1beta1;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.BuildableReference;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class LoadBalancerSettings implements Serializable {

  private final static long serialVersionUID = 1027350892521258125L;

  @JsonProperty("simple")
  private LoadBalancerSettingsSimple simple;

  @JsonProperty("consistentHash")
  private LoadBalancerSettingsConsistentHash consistentHash;

  /**
   *
   */
  @JsonProperty("localityLbSetting")
  @JsonPropertyDescription("")
  private LocalityLoadBalancerSetting localityLbSetting;

  public LoadBalancerSettingsSimple getSimple() {
    return simple;
  }

  public void setSimple(LoadBalancerSettingsSimple simple) {
    this.simple = simple;
  }

  public LoadBalancerSettingsConsistentHash getConsistentHash() {
    return consistentHash;
  }

  public void setConsistentHash(LoadBalancerSettingsConsistentHash consistentHash) {
    this.consistentHash = consistentHash;
  }

  public LocalityLoadBalancerSetting getLocalityLbSetting() {
    return localityLbSetting;
  }

  public void setLocalityLbSetting(LocalityLoadBalancerSetting localityLbSetting) {
    this.localityLbSetting = localityLbSetting;
  }

  public static final LoadBalancerSettings simple(LoadBalancerSettingsSimple simple) {
    LoadBalancerSettings settings = new LoadBalancerSettings();
    settings.simple = simple;
    return settings;
  }

  public static final LoadBalancerSettings consistentHash(LoadBalancerSettingsConsistentHash consistentHash) {
    LoadBalancerSettings settings = new LoadBalancerSettings();
    settings.consistentHash = consistentHash;
    return settings;
  }
}
