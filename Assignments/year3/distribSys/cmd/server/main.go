package main

import (
	"context"
	"os"
	"fmt"
	"log"
	"net"
	"strings"

	gamemanagerpb "distribSys/api/distrib_sys/v1"
	"distribSys/internal/manager"
	"distribSys/internal/mq"

	"google.golang.org/grpc"
)

type server struct {
	gamemanagerpb.UnimplementedGameManServiceServer
	gamemanagerpb.UnimplementedPangramServiceServer 
	mgr manager.Manager
	pub *mq.Publisher
}


func (s *server) CreateGame(ctx context.Context, req *gamemanagerpb.CreateGameRequest) (*gamemanagerpb.CreateGameResponse, error) {
	id, g, err := s.mgr.Create(req.GetKind()) 
	if err != nil {
		fmt.Printf("Failed to create game due to %v \n", err)
		return nil, err
	}
	return &gamemanagerpb.CreateGameResponse{Id: id, Type: g.Type()}, nil
}

func (s *server) SubmitGuess(ctx context.Context, req *gamemanagerpb.SubmitGuessRequest) (*gamemanagerpb.SubmitGuessResponse, error) {
	g, ok := s.mgr.Get(req.GetId())
	if !ok {
		return nil, fmt.Errorf("game not found")
	}

	newScore, wordsGuessed, statusMessage, isGameOver := g.ProcessGuess(req.GetGuess())

	if strings.Contains(statusMessage,"Pangram"){
	s.pub.Publish("game.pangram", fmt.Sprintf("Pangram found %v",req.GetGuess()))
	} 

	return &gamemanagerpb.SubmitGuessResponse{
		NewScore:     int32(newScore),
		WordsGuessed: wordsGuessed,
		Message:      statusMessage,
		GameOver:     isGameOver,
	}, nil
}


func (s *server) GetSummary(ctx context.Context, req *gamemanagerpb.GetSummaryRequest) (*gamemanagerpb.GetSummaryResponse, error) {
	g, ok := s.mgr.Get(req.GetId())
	if !ok {
		return nil, fmt.Errorf("game not found")
	}
	return &gamemanagerpb.GetSummaryResponse{GameId: req.GetId(), GameType: g.Type()}, nil
}


func (s *server) GetGameState(ctx context.Context, req *gamemanagerpb.GetGameStateRequest) (*gamemanagerpb.GetGameStateResponse, error) {
	g, ok := s.mgr.Get(req.GetId())
	if !ok {
		return nil, fmt.Errorf("game not found")
	}

	displayLetters, score := g.GetState()

	return &gamemanagerpb.GetGameStateResponse{
		DisplayLetters: displayLetters,
		CurrentScore:   int32(score),
	}, nil
}

func main() {
	lis, err := net.Listen("tcp", ":50051")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	rabbitmqURL := os.Getenv("RABBITMQ_URL") 
	if rabbitmqURL == "" {
		rabbitmqURL = "amqp://guest:guest@rabbitmq:5672/" 
	}

	pub, err := mq.Connect(rabbitmqURL)

	if err != nil {
		log.Fatalf("Failed to connect to RabbitMQ: %v", err)
	}
	defer pub.Close()
	s := &server{
		mgr: manager.Get(),
		pub: pub,
	}
	grpcServer := grpc.NewServer()

	gamemanagerpb.RegisterGameManServiceServer(grpcServer, s)
	gamemanagerpb.RegisterPangramServiceServer(grpcServer, s)

	log.Printf("server listening at %v", lis.Addr())

	if err := grpcServer.Serve(lis); err != nil {
		log.Fatal(err)
	}
}
