package main

import (
	"net"
	"fmt"
	"os"
	"slices"
	"strconv"
)

const (
	tcpAddr = ":9988"
	host = "localhost"
	serverType = "tcp"
)

var statOptions = []string{"avg","guesses","total","all","letters"}
var requiresGameID = []string{"avg", "guesses", "total", "letters"}

func main(){
	if len(os.Args) < 2 {
		fmt.Println("Usage: './cli <statistical option> [gameID]'")
		fmt.Println("Options:", statOptions)
		os.Exit(1)
	}

	userChoice := os.Args[1]
	
	if !slices.Contains(statOptions, userChoice) {
		fmt.Printf("Error: Invalid option '%s'\n", userChoice)
		fmt.Println("Options:", statOptions)
		os.Exit(1) 
	}

	var msg string 

	if slices.Contains(requiresGameID, userChoice) {
		if len(os.Args) != 3 {
			fmt.Printf("Usage: './cli %s <gameID>' - Missing game ID\n", userChoice)
			os.Exit(1)
		}
		
		gameIDStr := os.Args[2] 
		
		if _, err := strconv.Atoi(gameIDStr); err != nil {
			fmt.Printf("Error: Game ID '%s' must be a valid integer.\n", gameIDStr)
			os.Exit(1)
		}

		msg = fmt.Sprintf("%s,%s", userChoice, gameIDStr) 

	} else if userChoice == "all" {
		if len(os.Args) != 2 {
			fmt.Printf("Usage: './cli %s' - Too many arguments (expected 1)\n", userChoice)
			os.Exit(1)
		}
		
		msg = userChoice 
	}
    
	connection,err := net.Dial(serverType,host+tcpAddr)
	if err!=nil{
		panic(err)
	}
    defer connection.Close()

	_,err = connection.Write([]byte(msg))
	if err!=nil{
	    panic(err)
	}
	
	buffer:= make([]byte,1024)
	mLen,err := connection.Read(buffer)
	if err!=nil{
		fmt.Println("Error reading: ", err.Error())
		return
	}

	fmt.Printf("Received: %s\n", string(buffer[:mLen]))

}
