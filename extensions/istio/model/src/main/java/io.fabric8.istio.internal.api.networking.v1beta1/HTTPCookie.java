package io.fabric8.istio.internal.api.networking.v1beta1;

import java.io.Serializable;
import java.time.Duration;

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
  "name",
  "path",
  "ttl"
})
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
  @BuildableReference(ObjectMeta.class)
})
public class HTTPCookie implements Serializable {
  private final static long serialVersionUID = 1603761766655698247L;

  /**
   *
   */
  @JsonProperty("name")
  @JsonPropertyDescription("")
  private String name;
  /**
   *
   */
  @JsonProperty("path")
  @JsonPropertyDescription("")
  private String path;
  /**
   *
   */
  @JsonProperty("ttl")
  @JsonPropertyDescription("")
  private Duration ttl;

  /**
   * No args constructor for use in serialization
   */
  public HTTPCookie() {
  }

  /**
   * @param path
   * @param name
   * @param ttl
   */
  public HTTPCookie(String name, String path, Duration ttl) {
    super();
    this.name = name;
    this.path = path;
    this.ttl = ttl;
  }

  /**
   *
   */
  public String getName() {
    return name;
  }

  /**
   *
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   *
   */
  public String getPath() {
    return path;
  }

  /**
   *
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   *
   */
  public Duration getTtl() {
    return ttl;
  }

  /**
   *
   */
  public void setTtl(Duration ttl) {
    this.ttl = ttl;
  }

}
