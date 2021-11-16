package main

import (
	"fmt"
	"reflect"
	"strconv"

	proto "github.com/gogo/protobuf/proto"
)

type HTTPRedirect_RedirectPortSelection int32

const (
	HTTPRedirect_FROM_PROTOCOL_DEFAULT HTTPRedirect_RedirectPortSelection = 0
	HTTPRedirect_FROM_REQUEST_PORT     HTTPRedirect_RedirectPortSelection = 1
)

var HTTPRedirect_RedirectPortSelection_name = map[int32]string{
	0: "FROM_PROTOCOL_DEFAULT",
	1: "FROM_REQUEST_PORT",
}

var HTTPRedirect_RedirectPortSelection_value = map[string]int32{
	"FROM_PROTOCOL_DEFAULT": 0,
	"FROM_REQUEST_PORT":     1,
}

func (x HTTPRedirect_RedirectPortSelection) String() string {
	return proto.EnumName(HTTPRedirect_RedirectPortSelection_name, int32(x))
}

func main() {
	instanceForTesting := HTTPRedirect_RedirectPortSelection(1)
	t := reflect.TypeOf(instanceForTesting)
	instance := reflect.New(t).Elem()

	var index int64
	end := false
	index = 0
	for !end {
		instance.SetInt(index)
		value := fmt.Sprintf("%v", instance.Interface())
		if value == strconv.FormatInt(index, 10) {
			end = true
		} else {
			fmt.Println(value)
		}

		index++
	}

}
