package io.fabric8.istio.internal.api.security.v1beta1;

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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
  "apiVersion",
  "kind",
  "metadata",
  "from",
  "to",
  "when"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class Rule implements Serializable {

  private final static long serialVersionUID = -5861138005221591122L;

  /**
   *
   */
  @JsonProperty("from")
  @JsonPropertyDescription("")
  private List<From> from = new ArrayList<From>();
  /**
   *
   */
  @JsonProperty("to")
  @JsonPropertyDescription("")
  private List<To> to = new ArrayList<To>();
  /**
   *
   */
  @JsonProperty("when")
  @JsonPropertyDescription("")
  private List<Condition> when = new ArrayList<Condition>();

  /**
   * No args constructor for use in serialization
   *
   */
  public Rule() {
  }

  /**
   *
   * @param from
   * @param to
   * @param when
   */
  public Rule(List<From> from, List<To> to, List<Condition> when) {
    super();
    this.from = from;
    this.to = to;
    this.when = when;
  }

  /**
   *
   */
  public List<From> getFrom() {
    return from;
  }

  /**
   *
   */
  public void setFrom(List<From> from) {
    this.from = from;
  }

  /**
   *
   */
  public List<To> getTo() {
    return to;
  }

  /**
   *
   */
  public void setTo(List<To> to) {
    this.to = to;
  }

  /**
   *
   */
  public List<Condition> getWhen() {
    return when;
  }

  /**
   *
   */
  public void setWhen(List<Condition> when) {
    this.when = when;
  }
}
