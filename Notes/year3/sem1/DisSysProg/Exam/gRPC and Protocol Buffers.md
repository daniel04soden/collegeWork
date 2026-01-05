# gRPC

- Or google remote procedure call provides remote procedure calls with data serialisation and can work agnostically with Python,go,c++,nodejs, php and more
- Uses http/2 which is much faster than REST with HTTP/1.1
	- Bi directional communication supported, not just request response
	- Supports auth mechanisms like SSL/TLS

## How does gRPC work

### Services

- A service is defined, including methods that can be called and what parameters/types to use
- Server side runs the gRPC service whereas the client side has stub matching service provided by the service

### Protocol buffers

- AKA protobufs
- gRPC usually uses protocol buffers to serialise structured data
- Message payloads are binary therefore quite compact
- You create a .proto file that defines the service with messages containing fileds eg person.proto

- Then use the protoc compiler to compile the proto file for the language that you are going to use ie .pb.go, .pb.py

# Sample protobuf:

```go
syntax = "proto3";
package publish;

option go_package  = "/publish";

message Event {
  bytes content = 1;
  repeated string tags = 2;
}

message EventList {
  repeated Event events = 1;
}
```

## Three features of gRPC:

1. In contrast to Java's rmi, grpc is mostly language agnostic ie that the services messages enums and so on aren't tied to the specific language being worked with, if ever the service/codebase is rewritten from python to golang, the existing protobuf code will not need to be rewritten and can instead be recompiled with a different flag
2. Serialises data in a compact binary form and therefore has a much smaller memory footprint compared to something like xml or json when being sent over the network
3. Built-in Streaming Unlike traditional request-response models, gRPC natively supports four types of service methods thanks to its use of HTTP2/ Eg unary, binary,bidirectional streaming


## Steps involved in defining a grpc service.

1. Define service interface in proto file
2. Generate code from proto file
3. Implement server logic
4. Start grpc server
5. Implement client logic
6. call client and receive messages