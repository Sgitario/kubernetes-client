package io.fabric8.istio.internal.api.networking.v1beta1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.BuildableReference;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "apiVersion",
    "kind",
    "metadata",
    "distribute",
    "enabled",
    "failover"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class LocalityLoadBalancerSetting implements Serializable
{

    /**
     * 
     */
    @JsonProperty("distribute")
    @JsonPropertyDescription("")
    private List<Distribute> distribute = new ArrayList<Distribute>();
    @JsonProperty("enabled")
    private Boolean enabled;
    /**
     * 
     */
    @JsonProperty("failover")
    @JsonPropertyDescription("")
    private List<Failover> failover = new ArrayList<Failover>();
    private final static long serialVersionUID = -2941634603649521233L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LocalityLoadBalancerSetting() {
    }

    /**
     * 
     * @param failover
     * @param distribute
     * @param enabled
     */
    public LocalityLoadBalancerSetting(List<Distribute> distribute, Boolean enabled, List<Failover> failover) {
        super();
        this.distribute = distribute;
        this.enabled = enabled;
        this.failover = failover;
    }

    /**
     * 
     */
    public List<Distribute> getDistribute() {
        return distribute;
    }

    /**
     * 
     */
    public void setDistribute(List<Distribute> distribute) {
        this.distribute = distribute;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 
     */
    public List<Failover> getFailover() {
        return failover;
    }

    /**
     * 
     */
    public void setFailover(List<Failover> failover) {
        this.failover = failover;
    }

}
