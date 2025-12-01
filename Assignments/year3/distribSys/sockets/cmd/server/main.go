package main

import (
	"distribSys/sockets/dao"
	"fmt"
	"net"
	"os"
	"strings"
	"strconv"
)

const (
	tcpAddr  = ":9988"
	host = "localhost"
	serverType = "tcp"
)

var statOptions = []string{"avg","guesses","total","all","letters"}

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

	// String builder needed for reformatting parsed data
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
		
		for i := range games{
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
