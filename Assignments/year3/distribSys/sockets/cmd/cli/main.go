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
