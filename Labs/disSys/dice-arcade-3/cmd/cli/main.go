package main

import (
	"context"
	"dice-arcade/internal/manager/proxy"
	gamemanagerpb "dice-arcade/api/dicearcade/v1"
	"fmt"
	"os"
	"time"

	"google.golang.org/grpc"
)

func main() {
	if len(os.Args) < 2 {
		fmt.Println("usage: cli <game> (highlow|pig)")
		return
	}
	kind := os.Args[1]

	conn, err := grpc.Dial("localhost:50051", grpc.WithInsecure())
	if err != nil { panic(err) }
	defer conn.Close()

	raw := gamemanagerpb.NewGameManagerClient(conn)
	client := proxy.NewClientProxy(raw,5)


	// Create
	ctx, cancel := context.WithTimeout(context.Background(), 3*time.Second)
	defer cancel()
	cr, err := client.CreateGame(ctx, &gamemanagerpb.CreateGameRequest{Kind: kind})
	if err != nil { panic(err) }

	// Play once
	pr, err := client.PlayOnce(context.Background(), &gamemanagerpb.PlayOnceRequest{Id: cr.GetId()})
	if err != nil { panic(err) }

	fmt.Printf("[%s] %s\n", cr.GetId(), pr.GetOutcome())
}

func (s *server) GetSummary(ctx context.Context, req *gamemanagerpb.GetSummaryRequest) (*gamemanagerpb.GameSummaryResponse, error){ 
   // Fake data for demo purposes
   summary := &gamemanagerpb.GameSummaryResponse{
      GameId:   req.GetId(),
      GameName: "highlow",
      Rolls: []*gamemanagerpb.GameSummaryResponse_RollResult{
 j        {RollNumber: 1, Value: 5, Outcome: gamemanagerpb.Outcome_OUTCOME_WIN},
         {RollNumber: 2, Value: 3, Outcome: gamemanagerpb.Outcome_OUTCOME_LOSE},
         {RollNumber: 3, Value: 6, Outcome: gamemanagerpb.Outcome_OUTCOME_WIN},
      },
      TotalRolls: 3,
      TotalWins:  2,
   }
   return summary, nil
}

