/**
 * Copyright (C) 2021 Red Hat, Inc.
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
package main

import (
	"fmt"

	"github.com/gogo/protobuf/types"

	"github.com/fabric8io/kubernetes-client/generator/pkg/schemagen"
	// Internal APIs:
	api_networking_v1beta1 "istio.io/api/networking/v1beta1"
	// External APIs:
	client_networking_v1beta1 "istio.io/client-go/pkg/apis/networking/v1beta1"

	"reflect"
)

func main() {

	// the CRD List types for which the model should be generated
	// no other types need to be defined as they are auto discovered
	crdLists := map[reflect.Type]schemagen.CrdScope{
		// networking
		reflect.TypeOf(client_networking_v1beta1.DestinationRuleList{}): schemagen.Namespaced,
		reflect.TypeOf(client_networking_v1beta1.GatewayList{}):         schemagen.Namespaced,
		reflect.TypeOf(client_networking_v1beta1.ServiceEntryList{}):    schemagen.Namespaced,
		reflect.TypeOf(client_networking_v1beta1.SidecarList{}):         schemagen.Namespaced,
		reflect.TypeOf(client_networking_v1beta1.VirtualServiceList{}):  schemagen.Namespaced,
	}

	// constraints and patterns for fields
	constraints := map[reflect.Type]map[string]*schemagen.Constraint{}

	// types that are manually defined in the model
	providedTypes := []schemagen.ProvidedType{
		// Interfaces can't be automatically generated:
		{GoType: reflect.TypeOf(api_networking_v1beta1.StringMatch{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.StringMatch"},
		{GoType: reflect.TypeOf(api_networking_v1beta1.HTTPFaultInjection_Delay{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.Delay"},
		{GoType: reflect.TypeOf(api_networking_v1beta1.HTTPFaultInjection_Abort{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.Abort"},
		{GoType: reflect.TypeOf(api_networking_v1beta1.LoadBalancerSettings{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.LoadBalancerSettings"},
		{GoType: reflect.TypeOf(api_networking_v1beta1.LocalityLoadBalancerSetting{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.LocalityLoadBalancerSetting"},
		{GoType: reflect.TypeOf(api_networking_v1beta1.LocalityLoadBalancerSetting_Distribute{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.Distribute"},
		{GoType: reflect.TypeOf(api_networking_v1beta1.LocalityLoadBalancerSetting_Failover{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.Failover"},
		// Due to issue in sundrio that generates duplicated methods named `hasMatchingAllowOrigin`
		{GoType: reflect.TypeOf(api_networking_v1beta1.CorsPolicy{}), JavaClass: "io.fabric8.istio.internal.api.networking.v1beta1.CorsPolicy"},
	}

	// go packages that are provided and where no generation is required and their corresponding java package
	providedPackages := map[string]string{
		"k8s.io/apimachinery/pkg/apis/meta/v1": "io.fabric8.kubernetes.api.model",
	}

	// mapping of go packages of this module to the resulting java package
	// optional ApiGroup and ApiVersion for the go package (which is added to the generated java class)
	packageMapping := map[string]schemagen.PackageInformation{
		"istio.io/client-go/pkg/apis/networking/v1beta1": {JavaPackage: "io.fabric8.istio.api.networking.v1beta1", ApiGroup: "networking.istio.io", ApiVersion: "v1beta1"},
	}

	// converts all packages starting with <key> to a java package using an automated scheme:
	//  - replace <key> with <value> aka "package prefix"
	//  - replace '/' with '.' for a valid java package name
	mappingSchema := map[string]string{
		"istio.io/api": "io.fabric8.istio.internal.api",
	}

	// overwriting some times
	manualTypeMap := map[reflect.Type]string{
		// reflect.TypeOf(time.Time{}): "java.util.TimeZone",
		reflect.TypeOf(types.BoolValue{}):   "java.lang.Boolean",
		reflect.TypeOf(types.Duration{}):    "java.time.Duration",
		reflect.TypeOf(types.Timestamp{}):   "java.lang.Long",
		reflect.TypeOf(types.UInt32Value{}): "java.lang.Integer",
	}

	json := schemagen.GenerateSchema("http://fabric8.io/istio/IstioSchema#", crdLists, providedPackages, manualTypeMap, packageMapping, mappingSchema, providedTypes, constraints, "io.fabric8")

	fmt.Println(json)
}
