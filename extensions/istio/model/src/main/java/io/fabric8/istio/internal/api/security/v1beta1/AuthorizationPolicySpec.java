package io.fabric8.istio.internal.api.security.v1beta1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.istio.internal.api.type.v1beta1.WorkloadSelector;
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
  "action",
  "rules",
  "selector"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class AuthorizationPolicySpec implements Serializable {

  private final static long serialVersionUID = 7060730756144324497L;

  @JsonProperty("action")
  private AuthorizationPolicyAction action;
  /**
   *
   */
  @JsonProperty("rules")
  @JsonPropertyDescription("")
  private List<Rule> rules = new ArrayList<Rule>();
  /**
   *
   */
  @JsonProperty("selector")
  @JsonPropertyDescription("")
  private WorkloadSelector selector;

  /**
   * No args constructor for use in serialization
   */
  public AuthorizationPolicySpec() {
  }

  /**
   * @param action
   * @param rules
   * @param selector
   */
  public AuthorizationPolicySpec(AuthorizationPolicyAction action, List<Rule> rules, WorkloadSelector selector) {
    super();
    this.action = action;
    this.rules = rules;
    this.selector = selector;
  }

  public AuthorizationPolicyAction getAction() {
    return action;
  }

  public void setAction(AuthorizationPolicyAction action) {
    this.action = action;
  }

  /**
   *
   */
  public List<Rule> getRules() {
    return rules;
  }

  /**
   *
   */
  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  /**
   *
   */
  public WorkloadSelector getSelector() {
    return selector;
  }

  /**
   *
   */
  public void setSelector(WorkloadSelector selector) {
    this.selector = selector;
  }
}
