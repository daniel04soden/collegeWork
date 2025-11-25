package main

import (
	"context"
	"fmt"
	"log"
	"os"
	"time"

	gamemanagerpb "distribSys/api/distrib_sys/v1"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

func main() {
	var choice string
	fmt.Println("Welcome to the Spelling bee, What game would you line to play? [standard], [standard+logging]")
	_, err := fmt.Fscanln(os.Stdin, &choice)
	if err != nil && err.Error() != "unexpected newline" {
		log.Fatalf("failed to read choice: %v", err)
	}

	conn, err := grpc.NewClient("localhost:50051", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf("failed to create client: %v", err)
	}

	defer conn.Close()

	gameClient := gamemanagerpb.NewGameManServiceClient(conn)
	pangramClient := gamemanagerpb.NewPangramServiceClient(conn) 

	ctx, cancel := context.WithTimeout(context.Background(), 3*time.Second)
	defer cancel()

	cr, err := gameClient.CreateGame(ctx, &gamemanagerpb.CreateGameRequest{Kind: choice})
	if err != nil {
		log.Fatalf("failed to create game: %v", err)
	}
	gameID := cr.GetId()
	fmt.Printf("\n--- Game %s Created (Type: %s) ---\n", gameID, cr.GetType())

	gsr, err := pangramClient.GetGameState(ctx, &gamemanagerpb.GetGameStateRequest{Id: gameID})
	if err != nil {
		log.Fatalf("failed to get game state: %v", err)
	}

	fmt.Printf("Letters: %s\n", gsr.GetDisplayLetters())
	fmt.Printf("Current Score: %d\n", gsr.GetCurrentScore())
	fmt.Println("----------------------------------")

	for {
		var guess string
		fmt.Print("Enter a word: ")
		_, err := fmt.Fscanln(os.Stdin, &guess)
		if err != nil && err.Error() != "unexpected newline" {
			log.Printf("Error reading guess: %v. Ending game.", err)
			break
		}

		sgr, err := gameClient.SubmitGuess(context.Background(), &gamemanagerpb.SubmitGuessRequest{
			Id: gameID,
			Guess: guess,
		})

		if err != nil {
			log.Fatalf("Error submitting guess: %v. Ending game.", err)
			break
		}

		fmt.Printf("  -> Status: %s\n", sgr.GetMessage())
		fmt.Printf("  -> New Score: %d\n", sgr.GetNewScore())
		fmt.Printf("  -> Guessed Words: %v\n", sgr.GetWordsGuessed())

		if sgr.GetGameOver() {
			fmt.Println("\n--- Game Over! Congratulations! ---")
			break
		}
	}
}
