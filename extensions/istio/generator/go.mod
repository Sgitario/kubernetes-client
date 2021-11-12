module github.com/fabric8io/kubernetes-client/extensions/istio/generator

go 1.13

require (
  github.com/fabric8io/kubernetes-client/generator v0.0.0
	github.com/gogo/protobuf v1.3.2
	istio.io/api v0.0.0-20211012192923-310f2a3f3c76
	istio.io/client-go v1.11.4
)

replace (
  github.com/fabric8io/kubernetes-client/generator v0.0.0 => ./../../../generator
  k8s.io/apimachinery => k8s.io/apimachinery v0.19.7
  k8s.io/client-go => k8s.io/client-go v0.19.7
)
