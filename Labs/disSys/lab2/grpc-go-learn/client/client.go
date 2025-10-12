package main

import(
	"google.golang.org/grpc"
	"context"
	"log"
	"time"
	pb "grpc-go-learn/protobuf/grpc-go-learn/hello"
)

func main(){
	conn,err:= grpc.Dial("localhost:3000",grpc.WithInsecure(),grpc.WithBlock())
	if err!=nil{
		log.Fatalf("did not connect to %v", err)
	}
	
	defer conn.Close()
	
	c := pb.NewGreeterClient(conn)
	
	ctx,cancel := context.WithTimeout(context.Background(),time.Second)
	
	defer cancel()
	
	r,err:=c.SayHello(ctx,&pb.HelloRequest{Name:"world"})
	
	if err!=nil{
		log.Fatalf("could not greet: %v", err)
	}
	
	log.Printf("Greeting %s", r.GetMessage())
}
