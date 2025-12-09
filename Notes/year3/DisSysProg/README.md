# Daniel Soden Distributed Systems Assignment 2 - Spelling Bee Docker + Rabbitmq + Sockets

## Table of Contents
## Architecture
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
## Database Socket Client
### Database schema:

```sql
CREATE TABLE IF NOT EXISTS gameStatistics (
    gameID INTEGER PRIMARY KEY,
    totalScore REAL,
    averageScore REAL,
    guesses VARCHAR(255),
    letters VARCHAR(10)
);
```

```go
func storeGame(totalScore int, guesses []string, letters string) {
	avg := totalScore / len(guesses)
	db, err := sql.Open("sqlite3", dbPath)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()
	guessesStr := strings.Join(guesses, ",")

	sqlStmt := "INSERT INTO gameStatistics (totalScore, averageScore, guesses, letters) VALUES (?,?,?,?)"
	result, err := db.Exec(sqlStmt, totalScore, avg, guessesStr, letters)
	if err != nil {
		log.Fatal(err)
	}
	res, err := result.RowsAffected()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(res)
}
```

- The function above takes the info relating to the most recent game, calculates the average, extracts guesses and letters into strings and stores that within the database. 

```go
package main

import (
	"distribSys/dbsockets/dao"
	"fmt"
	"net"
	"os"
	"strconv"
	"strings"
)

const (
	tcpAddr    = ":9988"
	host       = "dbserver"
	serverType = "tcp"
)

var statOptions = []string{"avg", "guesses", "total", "all", "letters"}

func main() {
	fmt.Println("Running concurrent server...")

	server, err := net.Listen(serverType, host+tcpAddr)
	if err != nil {
		fmt.Println("Error Listening: ", err.Error())
		os.Exit(1)
	}

	defer server.Close()

	fmt.Println("Listening on " + host + tcpAddr)
	fmt.Println("Waiting for clients...")

	for {
		connection, err := server.Accept()
		if err != nil {
			fmt.Println("Error accepting: ", err.Error())
			continue
		}

		fmt.Println("Client connected from", connection.RemoteAddr())

		go processClientData(connection)
	}
}

func processClientData(connection net.Conn) {
	defer connection.Close()

	buffer := make([]byte, 1024)

	mLen, err := connection.Read(buffer)
	if err != nil {
		fmt.Printf("Error reading from %s: %s\n", connection.RemoteAddr(), err.Error())
		return
	}

	receivedMsg := string(buffer[:mLen])
	fmt.Printf("Received from %s: %s\n", connection.RemoteAddr(), receivedMsg)

	parts := strings.Split(receivedMsg, ",")
	option := parts[0]

	gameID := 0 // Default id

	if len(parts) == 2 {
		idStr := parts[1]
		parsedID, err := strconv.Atoi(idStr)
		if err != nil {
			response := []byte(fmt.Sprintf("ERROR: Game ID '%s' is not a valid number.", idStr))
			connection.Write(response)
			return
		}
		gameID = parsedID
	} else if len(parts) > 2 {
		response := []byte("ERROR: Invalid message format. Expected 'option' or 'option,gameID'.")
		connection.Write(response)
		return
	}

	var responseBuilder strings.Builder
	switch option {
	case statOptions[0]:
		avg, err := dao.GetAverage(gameID)
		if err != nil {
			fmt.Println("Error getting average:", err)
			connection.Write([]byte("ERROR: Failed to retrieve average."))
			return
		}
		fmt.Println("Average score:", avg)

		responseBuilder.WriteString(fmt.Sprintf("Average score for ID %d: %f", gameID, avg))

	case statOptions[1]:
		guesses, err := dao.GetGuesses(gameID)
		if err != nil {
			fmt.Println("Error getting guesses:", err)
			connection.Write([]byte("ERROR: Failed to retrieve guesses."))
			return
		}
		fmt.Println("Guesses:", guesses)

		responseBuilder.WriteString(fmt.Sprintf("Guesses for ID %d: %v", gameID, guesses))

	case statOptions[2]:
		total, err := dao.GetTotal(gameID)
		if err != nil {
			fmt.Println("Error getting total:", err)
			connection.Write([]byte("ERROR: Failed to retrieve total score."))
			return
		}
		fmt.Println("Total Score:", total)

		responseBuilder.WriteString(fmt.Sprintf("Total score for ID %d: %.0f", gameID, total))

	case statOptions[3]:

		games, err := dao.ListGames()
		if err != nil {
			fmt.Println("Error listing games:", err)
			connection.Write([]byte("ERROR: Failed to list all games."))
			return
		}

		for i := range games {
			output := fmt.Sprintf("Game ID: %d, Letters: %s, Guesses: %v, Avg: %.2f, Total: %.0f\n",
				games[i].ID, games[i].Letters, games[i].Guesses, games[i].Avg, games[i].Total)
			fmt.Print(output)
			responseBuilder.WriteString(output)
		}

	case statOptions[4]:
		letters, err := dao.GetLetters(gameID)
		if err != nil {
			fmt.Println("Error getting letters:", err)
			connection.Write([]byte("ERROR: Failed to retrieve letters."))
			return
		}
		fmt.Println("Letters: ", letters)

		responseBuilder.WriteString(fmt.Sprintf("Letters for ID %d: %s", gameID, letters))

	default:
		responseBuilder.WriteString(fmt.Sprintf("ERROR: Invalid statistical option '%s'", option))
	}

	_, err = connection.Write([]byte(responseBuilder.String()))
	if err != nil {
		fmt.Printf("Error writing response to %s: %s\n", connection.RemoteAddr(), err.Error())
	}

	fmt.Printf("Connection to %s closed.\n", connection.RemoteAddr())
}
```

- Above in the db socket client we see that it works in a very simple way, the client sends os arguments and depending on the amount of arguments different match cases are hit. The only possible command with one argument is all, which queries my data access object package that lists all games and gives any relevant info relating to each game
- The other messages include:
	- avg (Average)
	- total
	- guesses
	- letters
- Sending an argument does the following;
![[Pasted image 20251206165838.png]]
- After a game is saved we see that starting from 3 we query the database server, that extracts the game from the database, sends it to the server and we take the average field from that game and send it back to the client. I could have made it in such a way that it only extracts the average element from the database but it worked better this way for the rest of my application.
## Rabbitmq Socket client
- For the rabbitmq socket client application, apart from this line it is nearly entire indepedent:
  
  ```go
  cmd/server/main.go
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
	
	func (s *server) SubmitGuess(ctx context.Context, req *gamemanagerpb.SubmitGuessRequest) (*gamemanagerpb.SubmitGuessResponse, error) {
	g, ok := s.mgr.Get(req.GetId())
	if !ok {
		return nil, fmt.Errorf("game not found")
	}

	newScore, wordsGuessed, statusMessage, isGameOver := g.ProcessGuess(req.GetGuess())

	if strings.Contains(statusMessage, "Pangram") {
		s.pub.Publish("game.pangram", fmt.Sprintf("Pangram found %v", req.GetGuess()))
	}

	return &gamemanagerpb.SubmitGuessResponse{
		NewScore:     int32(newScore),
		WordsGuessed: wordsGuessed,
		Message:      statusMessage,
		GameOver:     isGameOver,
	}, nil
}
  ```

- When a guess is processed, a status message is always sent 
 ```go
 	statusMessage := ""
	if newPoints > 0 {
		s.wordsGuessed = append(s.wordsGuessed, guess)
		s.points += newPoints

		if newPoints > 7 {
			statusMessage = fmt.Sprintf("Correct! Pangram! (+%d points)", newPoints)
		} else {
			statusMessage = fmt.Sprintf("Correct! (+%d points)", newPoints)
		}

	} else {
		statusMessage = "Invalid word or word not found."
	}
  ```
  - These messages relate to the points accrued, if the word is valid but in particular, whether a pangram is found, the server checks if pangram is within that message and if found, a message is published through rabbit mq. 
```go
//cmd/cli/main.go

package main

import (
	"bufio"
	"fmt"
	"log"
	"net"
	"os"
	"strings"
)

func main() {
	addr := "localhost:9000"
	if len(os.Args) > 1 {
		addr = os.Args[1]
	}

	conn, err := net.Dial("tcp", addr)
	if err != nil {
		log.Fatalf("connect: %v", err)
	}
	defer conn.Close()

	serverReader := bufio.NewReader(conn)
	welcomeMsg, err := serverReader.ReadString('\n')
	if err != nil {
		log.Fatalf("Error reading welcome message from server: %v", err)
	}
	fmt.Print(strings.TrimRight(welcomeMsg, "\n") + "\n") 
	fmt.Printf("Connected to server at %s. Waiting for Pangram broadcasts...\n", addr)

	go func() {
 		for {
 			line, err := serverReader.ReadString('\n')
 			if err != nil {
				log.Printf("Error reading from server: %v. Disconnecting.", err)
				break 
			} else {
				fmt.Print(strings.TrimRight(line, "\n") + "\n")
 			}
 		}
 	}()

	<-make(chan struct{})
}
```

- Here we have our client who is ran in a goroutine with a channel and from there waits to see if any messages are sent from the server. 
```go
// cmd/server/main.go
package main

import (
	"bufio"
	"fmt"
	"log"
	"net"
	"strings"
	"sync"

	"github.com/streadway/amqp"
)

const (
	amqpURL = "amqp://guest:guest@rabbitmq:5672/"
	tcpAddr = ":9000"
)

var (
	mu      sync.Mutex
	clients = make(map[net.Conn]struct{})
)

func main() {

	conn, err := amqp.Dial(amqpURL)
	if err != nil {
		log.Fatalf("amqp dial: %v", err)
	}
	defer conn.Close()

	ch, err := conn.Channel()
	if err != nil {
		log.Fatalf("amqp channel: %v", err)
	}
	defer ch.Close()

	if err := ch.ExchangeDeclare(
		"game.events",
		"topic",
		true,
		false,
		false,
		false,
		nil); err != nil {
		log.Fatalf("exchange declare: %v", err)
	}

	q, err := ch.QueueDeclare("", false, true, true, false, nil)
	if err != nil {
		log.Fatalf("queue declare: %v", err)
	}

	if err := ch.QueueBind(q.Name, "game.pangram", "game.events", false, nil); err != nil {
		log.Fatalf("queue bind: %v", err)
	}

	deliveries, err := ch.Consume(q.Name, "", true, true, false, false, nil)
	if err != nil {
		log.Fatalf("consume: %v", err)
	}

	ln, err := net.Listen("tcp", tcpAddr)
	if err != nil {
		log.Fatalf("listen: %v", err)
	}
	log.Printf("Socket server listening on %s", tcpAddr)

	go func() {
		for d := range deliveries {
			payload := strings.TrimSpace(string(d.Body))
			broadcastMsg := fmt.Sprintf("\n*** PANGRAM FOUND! *** [%s] %s\n", d.RoutingKey, payload) 
			log.Println("BROADCASTING:", broadcastMsg)

			mu.Lock()

			for conn := range clients {
				if _, err := conn.Write([]byte(broadcastMsg)); err != nil {
					log.Printf("Error writing to client %s: %v. Disconnecting.", conn.RemoteAddr(), err)
					delete(clients, conn)
					conn.Close()
				}
			}
			mu.Unlock()
		}
		log.Println("RabbitMQ consumer ended")
	}()

	for {
		conn, err := ln.Accept()
		if err != nil {
			log.Printf("accept: %v", err)
			continue
		}

		mu.Lock()
		clients[conn] = struct{}{}
		mu.Unlock()

		go handleClient(conn)
	}
}
func handleClient(conn net.Conn) {
    log.Printf("Client %s connected.", conn.RemoteAddr())

    defer func() {
        mu.Lock()
        delete(clients, conn)
        mu.Unlock()
        conn.Close()
        log.Printf("Client %s disconnected.", conn.RemoteAddr())
    }()

    w := bufio.NewWriter(conn)

    _, err := w.WriteString("Welcome! Waiting for Pangram events...\n")
    if err != nil {
        log.Printf("Error writing initial welcome message to client %s: %v", conn.RemoteAddr(), err)
        return
    }
    _ = w.Flush()

    log.Printf("Client %s welcome message sent. Blocking handler.", conn.RemoteAddr())

    <-make(chan struct{})
    log.Printf("Client %s handler unblocked unexpectedly!", conn.RemoteAddr()) 
}
```
- In the server file through channels, go keyword and mutexes, we allow for multiple clients to concurrently be connected and wait to have a message be sent from the server to them.
- A lot of this code resembles that found in the dice-arcade on the rabbitmq portion however the channels and blocking was added so that rather than sending an os argument to check on if a pangram was found (multiple could be found) I made it a persistent running client so the server could send messages anytime any client has found a pangram.
- The broadcasted message is game.pangram which is the event instance when a pangram is found.
```go
package mq

import (
	"github.com/streadway/amqp"
	"log"
)

type Publisher struct{
 	conn *amqp.Connection
	channel *amqp.Channel
}

func Connect(amqpURL string)(*Publisher,error){
	conn,err:= amqp.Dial(amqpURL)
	if err!=nil{
		return nil,err
	}

	ch,err := conn.Channel()

	if err !=nil{
		conn.Close()
		return nil,err
	}

	err = ch.ExchangeDeclare(
		"game.events", 
		"topic", 
		true, 
		false, 
		false, 
		false, 
		nil,	
		)
	if err !=nil{
		conn.Close()
		return nil,err
	}

	return &Publisher{conn:conn,channel: ch},nil
}

func (p *Publisher) Publish(routingKey, message string) {
	err := p.channel.Publish(
		"game.events", 
		routingKey,    
		false,         
		false,         
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte(message),
		})
	if err != nil {
		log.Printf("Failed to publish message: %v", err)
	}
}

func (p *Publisher)Close(){
	p.channel.Close()
	p.conn.Close()
}

```
- As stated above, the code is very similar to the code used in dice-arcade and is just used as a simple helper to publish the game event
## Dockerfiles and docker compose 

- Originally my idea was simply to run each docker file separately with flags like what ports to access and what files and volumes to mount but after a lot trial and error I eventually realise docker compose is exactly what I was looking for to solve it all and make it significantly more convenient to run the entire application at once. 

- By the end I had a structure like so:
#### Docker compose file
```yml
services:
  rabbitmq:
    image: rabbitmq:management
    container_name: rabbitmq
    hostname: rabbitmq-host
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
    ports:
      - "5672:5672"
      - "15672:15672"
    healthcheck:
      test: ["CMD", "rabbitmq-diagnostics", "ping"]
      interval: 30s
      timeout: 30s
      retries: 3

  gameserver:
    build:
      context: .
      dockerfile: Dockerfile.Server
    container_name: game-server
    ports:
      - "50052:50051"
    depends_on:
      rabbitmq:
        condition: service_healthy
    environment:
      RABBITMQ_URL: amqp://guest:guest@rabbitmq:5672/
    command: ["./server-game-app"]

  dbserver:
    build:
      context: .
      dockerfile: Dockerfile.ServerDB
    container_name: db-server
    ports:
      - "9988:9988"
    command: ["./server-db"]

  mqserver:
    build:
      context: .
      dockerfile: Dockerfile.ServerMQ
    container_name: mq-server
    command: ["./server-mq"]
    ports:
      - "9000:9000"
    environment:
      RABBITMQ_URL: amqp://guest:guest@rabbitmq:5672/
    depends_on:
      rabbitmq:
        condition: service_healthy
```

- In this file we sequentially go through and run each image, rabbitmq is pulled down and run with the guest guest login and the other images like gameserver and mqserver are able to utitlise this in their dependencies so they can send messages to one another
- Db server is mostly independent and can be ran individually and its only interactions are between itself and the .db sqlite file. This is mounted within the docker file below through a copy command and kept within the container itself. The only time anything else accesses these files is when the game server client is writing to said database.
#### Game server docker file:

```Dockerfile
FROM golang:1.25-alpine AS builder

WORKDIR /app

COPY go.mod go.sum ./

RUN go mod download

COPY . .

RUN apk add --no-cache gcc musl-dev
RUN GOOS=linux go build -o server-game-app ./cmd/server

FROM alpine:latest

WORKDIR /root/

EXPOSE 50051

COPY --from=builder /app/server-game-app .
COPY db ./db/
COPY internal/data ./internal/data/

CMD ["./server-game-app"]
```

- While mostly explained above, other interesting commands include not disabling cgo like in most recommendations for docker files in go as we need cgo to be able to utilise sqlite3 modules. On top of this we have to copy internal/data and db as we need to read the word dictionary json and the database file to write to it.
#### DBServer docker file

```Dockerfile
FROM golang:1.25-alpine AS builder

WORKDIR /app

COPY go.mod go.sum ./

RUN go mod download

COPY . .
RUN apk add --no-cache gcc musl-dev
RUN GOOS=linux go build -o server-db ./dbsockets/cmd/server/ 


FROM alpine:latest
EXPOSE 9988

WORKDIR /root/

COPY --from=builder /app/server-db .
COPY db ./db/

CMD ["server-db"]
```
- This dockerfile also follows the same process except we do not need to copy word dictionary
#### MQServer file

```Dockerfile
FROM golang:1.25-alpine AS builder

WORKDIR /app

COPY go.mod go.sum ./

RUN go mod download

COPY . .

RUN apk add --no-cache gcc musl-dev
RUN GOOS=linux go build -o server-mq ./sockets/cmd/server/

FROM alpine:latest

EXPOSE 9000

WORKDIR /root/

COPY --from=builder /app/server-mq .

CMD ["server-mq"]
```
- The mqserver has no unique properties as dependencies and other interesting points are primarily handled in the compose file

- Each server component acts as their own server therefore it made the most sense for me to put them in their own containers and run them all simultaneously in the dockercompose file so all it is a case of is getting a client to individually connect to the game which would then be able to send signals to the rabbitmq server and that server could send signals to its clients if a pangram was found. This system allows me to essentially have one command to get the entire operation up and running. 