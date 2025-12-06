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
