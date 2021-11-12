
package io.fabric8.istio.internal.api.telemetry.v1alpha1;

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
public class CustomTag implements Serializable {
  private final static long serialVersionUID = -4959572692661659393L;

  @JsonProperty("literal")
  private Literal literal;

  @JsonProperty("environment")
  private Environment environment;

  @JsonProperty("header")
  private RequestHeader header;

  public Literal getLiteral() {
    return literal;
  }

  public void setLiteral(Literal literal) {
    this.literal = literal;
  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public RequestHeader getHeader() {
    return header;
  }

  public void setHeader(RequestHeader header) {
    this.header = header;
  }
}
