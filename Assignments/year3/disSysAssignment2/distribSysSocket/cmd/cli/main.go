package main

import (
	"context"
	"fmt"
	"log"
	"time"

	gamemanagerpb "distribSys/api/distrib_sys/v1"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

func main() {
	var choice string
	fmt.Println("Welcome to the Spelling bee, What game would you line to play ? [standard], [standard+logging]")
	fmt.Scanln(&choice)

	conn, err := grpc.NewClient("localhost:50051", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf("failed to create client: %v", err)
	}

	defer conn.Close()

	client := gamemanagerpb.NewGameManServiceClient(conn)
	ctx, cancel := context.WithTimeout(context.Background(), 3*time.Second)
	defer cancel()

	cr, err := client.CreateGame(ctx, &gamemanagerpb.CreateGameRequest{Kind: choice})
	if err != nil {
		panic(err)
	}

	pr, err := client.PlayGame(context.Background(), &gamemanagerpb.PlayGameRequest{Id: cr.GetId()})
	fmt.Println("Score: ", pr.GetScore())
	if err != nil {
		panic(err)
	}
}
