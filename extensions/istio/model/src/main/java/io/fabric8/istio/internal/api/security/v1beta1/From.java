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
  "source"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class From implements Serializable {

  private final static long serialVersionUID = 386137345033826692L;

  /**
   *
   */
  @JsonProperty("source")
  @JsonPropertyDescription("")
  private Source source;

  /**
   * No args constructor for use in serialization
   *
   */
  public From() {
  }

  /**
   *
   * @param source
   */
  public From(Source source) {
    super();
    this.source = source;
  }

  /**
   *
   */
  public Source getSource() {
    return source;
  }

  /**
   *
   */
  public void setSource(Source source) {
    this.source = source;
  }

}
