package io.fabric8.istio.internal.api.security.v1beta1;

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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
  "apiVersion",
  "kind",
  "metadata",
  "operation"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class To implements Serializable {

  private final static long serialVersionUID = 4479473569927253856L;

  /**
   *
   */
  @JsonProperty("operation")
  @JsonPropertyDescription("")
  private Operation operation;

  /**
   * No args constructor for use in serialization
   *
   */
  public To() {
  }

  /**
   *
   * @param operation
   */
  public To(Operation operation) {
    super();
    this.operation = operation;
  }

  /**
   *
   */
  public Operation getOperation() {
    return operation;
  }

  /**
   *
   */
  public void setOperation(Operation operation) {
    this.operation = operation;
  }

}
