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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@JsonPropertyOrder({
  "apiVersion",
  "kind",
  "metadata",
  "errorType",
  "percentage"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class Abort implements Serializable {

  @JsonProperty("errorType")
  @JsonUnwrapped
  private Abort.ErrorType errorType;
  /**
   *
   */
  @JsonProperty("percentage")
  @JsonPropertyDescription("")
  private Percent percentage;
  private final static long serialVersionUID = 7834884103137184534L;

  /**
   * No args constructor for use in serialization
   */
  public Abort() {
  }

  /**
   * @param errorType
   * @param percentage
   */
  public Abort(Abort.ErrorType errorType, Percent percentage) {
    super();
    this.errorType = errorType;
    this.percentage = percentage;
  }

  public Abort.ErrorType getErrorType() {
    return errorType;
  }

  public void setErrorType(Abort.ErrorType errorType) {
    this.errorType = errorType;
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

  public interface ErrorType extends Serializable {

  }

}
