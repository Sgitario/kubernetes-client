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
  "ipBlocks",
  "namespaces",
  "notIpBlocks",
  "notNamespaces",
  "notPrincipals",
  "notRequestPrincipals",
  "principals",
  "requestPrincipals"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class Source implements Serializable {

  private final static long serialVersionUID = 6824747862373679525L;

  /**
   *
   */
  @JsonProperty("ipBlocks")
  @JsonPropertyDescription("")
  private List<String> ipBlocks = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("namespaces")
  @JsonPropertyDescription("")
  private List<String> namespaces = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notIpBlocks")
  @JsonPropertyDescription("")
  private List<String> notIpBlocks = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notNamespaces")
  @JsonPropertyDescription("")
  private List<String> notNamespaces = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notPrincipals")
  @JsonPropertyDescription("")
  private List<String> notPrincipals = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notRequestPrincipals")
  @JsonPropertyDescription("")
  private List<String> notRequestPrincipals = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("principals")
  @JsonPropertyDescription("")
  private List<String> principals = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("requestPrincipals")
  @JsonPropertyDescription("")
  private List<String> requestPrincipals = new ArrayList<String>();

  /**
   * No args constructor for use in serialization
   *
   */
  public Source() {
  }

  /**
   *
   * @param notRequestPrincipals
   * @param notIpBlocks
   * @param ipBlocks
   * @param notNamespaces
   * @param principals
   * @param notPrincipals
   * @param requestPrincipals
   * @param namespaces
   */
  public Source(List<String> ipBlocks, List<String> namespaces, List<String> notIpBlocks, List<String> notNamespaces, List<String> notPrincipals, List<String> notRequestPrincipals, List<String> principals, List<String> requestPrincipals) {
    super();
    this.ipBlocks = ipBlocks;
    this.namespaces = namespaces;
    this.notIpBlocks = notIpBlocks;
    this.notNamespaces = notNamespaces;
    this.notPrincipals = notPrincipals;
    this.notRequestPrincipals = notRequestPrincipals;
    this.principals = principals;
    this.requestPrincipals = requestPrincipals;
  }

  /**
   *
   */
  public List<String> getIpBlocks() {
    return ipBlocks;
  }

  /**
   *
   */
  public void setIpBlocks(List<String> ipBlocks) {
    this.ipBlocks = ipBlocks;
  }

  /**
   *
   */
  public List<String> getNamespaces() {
    return namespaces;
  }

  /**
   *
   */
  public void setNamespaces(List<String> namespaces) {
    this.namespaces = namespaces;
  }

  /**
   *
   */
  public List<String> getNotIpBlocks() {
    return notIpBlocks;
  }

  /**
   *
   */
  public void setNotIpBlocks(List<String> notIpBlocks) {
    this.notIpBlocks = notIpBlocks;
  }

  /**
   *
   */
  public List<String> getNotNamespaces() {
    return notNamespaces;
  }

  /**
   *
   */
  public void setNotNamespaces(List<String> notNamespaces) {
    this.notNamespaces = notNamespaces;
  }

  /**
   *
   */
  public List<String> getNotPrincipals() {
    return notPrincipals;
  }

  /**
   *
   */
  public void setNotPrincipals(List<String> notPrincipals) {
    this.notPrincipals = notPrincipals;
  }

  /**
   *
   */
  public List<String> getNotRequestPrincipals() {
    return notRequestPrincipals;
  }

  /**
   *
   */
  public void setNotRequestPrincipals(List<String> notRequestPrincipals) {
    this.notRequestPrincipals = notRequestPrincipals;
  }

  /**
   *
   */
  public List<String> getPrincipals() {
    return principals;
  }

  /**
   *
   */
  public void setPrincipals(List<String> principals) {
    this.principals = principals;
  }

  /**
   *
   */
  public List<String> getRequestPrincipals() {
    return requestPrincipals;
  }

  /**
   *
   */
  public void setRequestPrincipals(List<String> requestPrincipals) {
    this.requestPrincipals = requestPrincipals;
  }

}
