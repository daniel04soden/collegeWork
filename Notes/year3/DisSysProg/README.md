# Daniel Soden Distributed Systems Assignment 2 - Spelling Bee Docker + Rabbitmq + Sockets

## Table of contents

# Table of Contents
1. [Architecture](#Architecture)
2. [Database Socket Client](#Database Socket Client)
3. [Rabbitmq Socket Client](#Rabbitmq Socket Client)
4. [Dockerfiles and docker compose](#Dockerfiles and docker compose)


# Architecture
![[Screenshot 2025-12-06 at 12.50.30.png]]

- My idea for the design of this system was initially to have it so it works similar to last time wherein a client connects to our game server and is given a couple of letters and from there has to play the spelling bee game. However I had to refactor my original protobuf file a good bit as in my previous design, there was no way to track game state so whenever a client would connect, the game state would all be stored on the server and the game was also running on the server. After a good chunk of refactoring I ended up with something like this 
  
  
  ```go
  // cmd/cli/main.go
func main() {
	var choice string
	fmt.Println("Welcome to the Spelling bee, What game would you line to play? [standard], [standard+logging]")
	_, err := fmt.Fscanln(os.Stdin, &choice)
	if err != nil && err.Error() != "unexpected newline" {
		log.Fatalf("failed to read choice: %v", err)
	}
	```
- The user is prompted to decide again like last time if they'd like to play as normal, or as normal with logging on the server end

	```go
	conn, err := grpc.NewClient("localhost:50052", grpc.WithTransportCredentials(insecure.NewCredentials()))
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
	```
- From here in the protobuf file, we have go code to request the creation of both pangrams (moreso the letters to guess pangrams) and a game struct. This game is then stored as a state so the user can be informed whether or not their guess was valid, their points so far, have they gotten a pangram etc etc.
	
	``` go
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

```
- Next we have the standard game loop, the user receives the display letters and their score from the game state, from there they are able to enter a guess, the gameClient receives these guesses, using an internal function which uses some of the internal builders that verifies whether their guess is a real word, if it has the letters and then finally it proceses how many points said guess should receive (+7 if pangram). If a guess causes a major error (unlikely) the game can terminate
```go
		fmt.Printf("  -> Status: %s\n", sgr.GetMessage())
		fmt.Printf("  -> New Score: %d\n", sgr.GetNewScore())
		fmt.Printf("  -> Guessed Words: %v\n", sgr.GetWordsGuessed())
		if sgr.GetGameOver() {
			storeGame( int(sgr.GetNewScore()), sgr.GetWordsGuessed(), gsr.GetDisplayLetters())
			fmt.Println("\n--- Game Over! Congratulations! ---")
			break
		}
	}
}
```

- Finally once the gameover condition is met (any score > 25), we receive a game over message and the game breaks. Next we will go onto the database socket client setup, which the game client is also involved in
# Database Socket Client
# Rabbitmq Socket client
# Dockerfiles and docker compose 

