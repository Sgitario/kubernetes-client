package io.fabric8.istio.internal.api.networking.v1beta1;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.sundr.builder.annotations.Buildable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = com.fasterxml.jackson.databind.JsonDeserializer.None.class)
@ToString
@EqualsAndHashCode
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = false, builderPackage = "io.fabric8.kubernetes.api.builder")
public class StringMatch implements Serializable {
    private final static long serialVersionUID = -8503361184326500619L;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("exact")
    private String exact;

    @JsonProperty("regex")
    private String regex;

    public String getPrefix() {
      return prefix;
    }

    public void setPrefix(String prefix) {
      this.prefix = prefix;
    }

    public String getExact() {
      return exact;
    }

    public void setExact(String exact) {
      this.exact = exact;
    }

    public String getRegex() {
      return regex;
    }

    public void setRegex(String regex) {
      this.regex = regex;
    }

    public static final StringMatch prefix(String prefix) {
        StringMatch match = new StringMatch();
        match.prefix = prefix;
        return match;
    }

    public static final StringMatch exact(String exact) {
        StringMatch match = new StringMatch();
        match.exact = exact;
        return match;
    }

    public static final StringMatch regex(String regex) {
        StringMatch match = new StringMatch();
        match.regex = regex;
        return match;
    }
}
