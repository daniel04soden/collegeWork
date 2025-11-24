package main

import (
	"context"
	"fmt"
	"log"
	"net"

	gamemanagerpb "distribSys/api/distrib_sys/v1"
	"distribSys/internal/manager"

	"google.golang.org/grpc"
)

type server struct {
	gamemanagerpb.UnimplementedGameManServiceServer
	mgr manager.Manager
}

func (s *server) CreateGame(ctx context.Context, req *gamemanagerpb.CreateGameRequest) (*gamemanagerpb.CreateGameResponse, error) {
	id, g, err := s.mgr.Create(req.GetKind())
	if err != nil {
		fmt.Printf("Failed to play game due to %v \n", err)
		return nil, err
	}
	return &gamemanagerpb.CreateGameResponse{Id: id, Type: g.Type()}, nil
}

func (s *server) PlayGame(ctx context.Context, req *gamemanagerpb.PlayGameRequest) (*gamemanagerpb.PlayGameResponse, error) {
	g, ok := s.mgr.Get(req.GetId())
	if !ok {
		return nil, fmt.Errorf("game not found")
	}
	score, guesses := g.Play()
	return &gamemanagerpb.PlayGameResponse{Score: int32(score), Guesses: guesses}, nil
}

func main() {
	lis, err := net.Listen("tcp", ":50051")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	s := &server{mgr: manager.Get()}
	grpcServer := grpc.NewServer()
	gamemanagerpb.RegisterGameManServiceServer(grpcServer, s)

	log.Printf("server listening at %v", lis.Addr())

	if err := grpcServer.Serve(lis); err != nil {
		log.Fatal(err)
	}
}
