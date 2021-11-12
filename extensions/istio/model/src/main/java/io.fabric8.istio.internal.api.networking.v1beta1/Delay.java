package io.fabric8.istio.internal.api.networking.v1beta1;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
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
@JsonPropertyOrder({
    "apiVersion",
    "kind",
    "metadata",
    "httpDelayType",
    "percent",
    "percentage"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
    @BuildableReference(ObjectMeta.class)
})
public class Delay implements Serializable
{

    @JsonProperty("httpDelayType")
    @JsonUnwrapped
    private Delay.HttpDelayType httpDelayType;
    /**
     * 
     */
    @JsonProperty("percent")
    @JsonPropertyDescription("")
    private Integer percent;
    /**
     * 
     */
    @JsonProperty("percentage")
    @JsonPropertyDescription("")
    private Percent percentage;
    private final static long serialVersionUID = -6345866966981294208L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Delay() {
    }

    /**
     * 
     * @param percentage
     * @param httpDelayType
     * @param percent
     */
    public Delay(Delay.HttpDelayType httpDelayType, Integer percent, Percent percentage) {
        super();
        this.httpDelayType = httpDelayType;
        this.percent = percent;
        this.percentage = percentage;
    }

    public Delay.HttpDelayType getHttpDelayType() {
        return httpDelayType;
    }

    public void setHttpDelayType(Delay.HttpDelayType httpDelayType) {
        this.httpDelayType = httpDelayType;
    }

    /**
     * 
     */
    public Integer getPercent() {
        return percent;
    }

    /**
     * 
     */
    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    /**
     * 
     */
    public Percent getPercentage() {
        return percentage;
    }

    /**
     * 
     */
    public void setPercentage(Percent percentage) {
        this.percentage = percentage;
    }

    public interface HttpDelayType extends Serializable
    {


    }

}
