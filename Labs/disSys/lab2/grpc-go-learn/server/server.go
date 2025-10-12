package main

import (
	"context"
	"log"
	"net"
	"google.golang.org/grpc"
	pb "grpc-go-learn/protobuf/grpc-go-learn/hello"
)

type server struct {
	pb.UnimplementedGreeterServer
}

func (s *server) SayHello(ctx context.Context, in *pb.HelloRequest) (*pb.HelloReply, error){
	return &pb.HelloReply{Message: "Hello " + in.Name},nil
}

func main(){
	lis,err :=net.Listen("tcp","localhost:3000")
	if err!=nil{
	  log.Fatalf("Failed to listen %v",err)
	}
	s:=grpc.NewServer()
	pb.RegisterGreeterServer(s,&server{})
	log.Printf("Server listening at %v ", lis.Addr())
	if err := s.Serve(lis); err!=nil{
	log.Fatalf("Failed to serve %v ", err)
}
}
