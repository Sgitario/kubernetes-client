package io.fabric8.istio.internal.api.networking.v1beta1;

import java.io.Serializable;

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
    "from",
    "to"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class Failover implements Serializable
{
    private final static long serialVersionUID = -125581227124762700L;

    /**
     * 
     */
    @JsonProperty("from")
    @JsonPropertyDescription("")
    private String from;
    /**
     * 
     */
    @JsonProperty("to")
    @JsonPropertyDescription("")
    private String to;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Failover() {
    }

    /**
     * 
     * @param from
     * @param to
     */
    public Failover(String from, String to) {
        super();
        this.from = from;
        this.to = to;
    }

    /**
     * 
     */
    public String getFrom() {
        return from;
    }

    /**
     * 
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * 
     */
    public String getTo() {
        return to;
    }

    /**
     * 
     */
    public void setTo(String to) {
        this.to = to;
    }

}
