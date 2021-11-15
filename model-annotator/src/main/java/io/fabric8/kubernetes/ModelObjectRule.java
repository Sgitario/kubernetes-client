/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.kubernetes;

import org.jsonschema2pojo.Schema;
import org.jsonschema2pojo.rules.ObjectRule;
import org.jsonschema2pojo.rules.RuleFactory;
import org.jsonschema2pojo.util.ParcelableHelper;
import org.jsonschema2pojo.util.ReflectionHelper;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;

/**
 * Class that extend the object rule to add support of interfaces.
 */
public class ModelObjectRule extends ObjectRule {

  public static final String INTERFACE_TYPE_PROPERTY = "interfaceType";

  private final RuleFactory ruleFactory;

  protected ModelObjectRule(RuleFactory ruleFactory, ParcelableHelper parcelableHelper, ReflectionHelper reflectionHelper) {
    super(ruleFactory, parcelableHelper, reflectionHelper);

    this.ruleFactory = ruleFactory;
  }

  @Override
  public JType apply(String nodeName, JsonNode node, JsonNode parent, JPackage _package, Schema schema) {
    if (node.has(INTERFACE_TYPE_PROPERTY)) {
      // interface
      return createInterface(node, _package);
    }

    // rest of types
    return super.apply(nodeName, node, parent, _package, schema);
  }

  private JType createInterface(JsonNode node, JPackage _package) {
    String fqn = node.path(INTERFACE_TYPE_PROPERTY).asText();
    int index = fqn.lastIndexOf(".") + 1;

    JDefinedClass newType;
    try {
      newType = _package._interface(fqn.substring(index));
    } catch (JClassAlreadyExistsException ex) {
      return ex.getExistingClass();
    }

    this.ruleFactory.getAnnotator().typeInfo(newType, node);
    this.ruleFactory.getAnnotator().propertyInclusion(newType, node);
    return newType;
  }
}
