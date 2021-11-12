package io.fabric8.istio.internal.api.networking.v1beta1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerPort;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.VolumeMount;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.BuildableReference;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "allow_credentials",
    "allow_headers",
    "allow_methods",
    "allow_origin",
    "allow_origins",
    "expose_headers",
    "max_age"
})
@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder", refs = {
    @BuildableReference(ObjectMeta.class),
    @BuildableReference(ObjectReference.class),
    @BuildableReference(LabelSelector.class),
    @BuildableReference(Container.class),
    @BuildableReference(EnvVar.class),
    @BuildableReference(ContainerPort.class),
    @BuildableReference(Volume.class),
    @BuildableReference(VolumeMount.class)
})
public class CorsPolicy implements KubernetesResource
{

    @JsonProperty("allow_credentials")
    private Boolean allowCredentials;
    @JsonProperty("allow_headers")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> allowHeaders = new ArrayList<String>();
    @JsonProperty("allow_methods")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> allowMethods = new ArrayList<String>();
    @JsonProperty("allow_origin")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> deprecatedAllowOrigin = new ArrayList<String>();
    @JsonProperty("allow_origins")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<StringMatch> allowOrigins = new ArrayList<StringMatch>();
    @JsonProperty("expose_headers")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> exposeHeaders = new ArrayList<String>();
    @JsonProperty("max_age")
    private Duration maxAge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public CorsPolicy() {
    }

    /**
     * 
     * @param allowMethods
     * @param allowHeaders
     * @param exposeHeaders
     * @param maxAge
     * @param allowCredentials
     * @param deprecatedAllowOrigin
     * @param allowOrigins
     */
    public CorsPolicy(Boolean allowCredentials, List<String> allowHeaders, List<String> allowMethods, List<String> deprecatedAllowOrigin, List<StringMatch> allowOrigins, List<String> exposeHeaders, Duration maxAge) {
        super();
        this.allowCredentials = allowCredentials;
        this.allowHeaders = allowHeaders;
        this.allowMethods = allowMethods;
        this.deprecatedAllowOrigin = deprecatedAllowOrigin;
        this.allowOrigins = allowOrigins;
        this.exposeHeaders = exposeHeaders;
        this.maxAge = maxAge;
    }

    @JsonProperty("allow_credentials")
    public Boolean getAllowCredentials() {
        return allowCredentials;
    }

    @JsonProperty("allow_credentials")
    public void setAllowCredentials(Boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    @JsonProperty("allow_headers")
    public List<String> getAllowHeaders() {
        return allowHeaders;
    }

    @JsonProperty("allow_headers")
    public void setAllowHeaders(List<String> allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    @JsonProperty("allow_methods")
    public List<String> getAllowMethods() {
        return allowMethods;
    }

    @JsonProperty("allow_methods")
    public void setAllowMethods(List<String> allowMethods) {
        this.allowMethods = allowMethods;
    }

    @JsonProperty("allow_origin")
    public List<String> getDeprecatedAllowOrigin() {
        return deprecatedAllowOrigin;
    }

    @JsonProperty("allow_origin")
    public void setDeprecatedAllowOrigin(List<String> deprecatedAllowOrigin) {
        this.deprecatedAllowOrigin = deprecatedAllowOrigin;
    }

    @JsonProperty("allow_origins")
    public List<StringMatch> getAllowOrigins() {
        return allowOrigins;
    }

    @JsonProperty("allow_origins")
    public void setAllowOrigins(List<StringMatch> allowOrigins) {
        this.allowOrigins = allowOrigins;
    }

    @JsonProperty("expose_headers")
    public List<String> getExposeHeaders() {
        return exposeHeaders;
    }

    @JsonProperty("expose_headers")
    public void setExposeHeaders(List<String> exposeHeaders) {
        this.exposeHeaders = exposeHeaders;
    }

    @JsonProperty("max_age")
    public Duration getMaxAge() {
        return maxAge;
    }

    @JsonProperty("max_age")
    public void setMaxAge(Duration maxAge) {
        this.maxAge = maxAge;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
