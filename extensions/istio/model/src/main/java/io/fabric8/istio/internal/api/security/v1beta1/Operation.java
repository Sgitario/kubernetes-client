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
  "hosts",
  "methods",
  "notHosts",
  "notMethods",
  "notPaths",
  "notPorts",
  "paths",
  "ports"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class Operation implements Serializable {

  private final static long serialVersionUID = 1914815729875204151L;

  /**
   *
   */
  @JsonProperty("hosts")
  @JsonPropertyDescription("")
  private List<String> hosts = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("methods")
  @JsonPropertyDescription("")
  private List<String> methods = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notHosts")
  @JsonPropertyDescription("")
  private List<String> notHosts = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notMethods")
  @JsonPropertyDescription("")
  private List<String> notMethods = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notPaths")
  @JsonPropertyDescription("")
  private List<String> notPaths = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("notPorts")
  @JsonPropertyDescription("")
  private List<String> notPorts = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("paths")
  @JsonPropertyDescription("")
  private List<String> paths = new ArrayList<String>();
  /**
   *
   */
  @JsonProperty("ports")
  @JsonPropertyDescription("")
  private List<String> ports = new ArrayList<String>();

  /**
   * No args constructor for use in serialization
   *
   */
  public Operation() {
  }

  /**
   *
   * @param notMethods
   * @param hosts
   * @param methods
   * @param paths
   * @param notPaths
   * @param notHosts
   * @param ports
   * @param notPorts
   */
  public Operation(List<String> hosts, List<String> methods, List<String> notHosts, List<String> notMethods, List<String> notPaths, List<String> notPorts, List<String> paths, List<String> ports) {
    super();
    this.hosts = hosts;
    this.methods = methods;
    this.notHosts = notHosts;
    this.notMethods = notMethods;
    this.notPaths = notPaths;
    this.notPorts = notPorts;
    this.paths = paths;
    this.ports = ports;
  }

  /**
   *
   */
  public List<String> getHosts() {
    return hosts;
  }

  /**
   *
   */
  public void setHosts(List<String> hosts) {
    this.hosts = hosts;
  }

  /**
   *
   */
  public List<String> getMethods() {
    return methods;
  }

  /**
   *
   */
  public void setMethods(List<String> methods) {
    this.methods = methods;
  }

  /**
   *
   */
  public List<String> getNotHosts() {
    return notHosts;
  }

  /**
   *
   */
  public void setNotHosts(List<String> notHosts) {
    this.notHosts = notHosts;
  }

  /**
   *
   */
  public List<String> getNotMethods() {
    return notMethods;
  }

  /**
   *
   */
  public void setNotMethods(List<String> notMethods) {
    this.notMethods = notMethods;
  }

  /**
   *
   */
  public List<String> getNotPaths() {
    return notPaths;
  }

  /**
   *
   */
  public void setNotPaths(List<String> notPaths) {
    this.notPaths = notPaths;
  }

  /**
   *
   */
  public List<String> getNotPorts() {
    return notPorts;
  }

  /**
   *
   */
  public void setNotPorts(List<String> notPorts) {
    this.notPorts = notPorts;
  }

  /**
   *
   */
  public List<String> getPaths() {
    return paths;
  }

  /**
   *
   */
  public void setPaths(List<String> paths) {
    this.paths = paths;
  }

  /**
   *
   */
  public List<String> getPorts() {
    return ports;
  }

  /**
   *
   */
  public void setPorts(List<String> ports) {
    this.ports = ports;
  }

}
