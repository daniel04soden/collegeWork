package main

import (
	"fmt"
	"log"
	"net/http"
	"text/template"
	"time"
	// "golang.org/x/crypto/bcrypt" - will need soon for password encryption
)

type User struct {
	id        int64
	firstName string
	lastName  string
	DOB       time.Time // Only date
	email     string
	username  string
	password  string
}

type Ticket struct { // Tickets of features to add to site/other queries
	concernedUser User      // User that made the ticket
	timeOfTicket  time.Time // Date and time of ticket
	ticketID      int64     // Unique id of ticket
	subject       string    // Overall subject for ticket
	content       string    // Content of what they want in the ticket
}

func main() {
	var port = ":3000"
	fmt.Println("Sever started on port ", port)
	initPage := func(w http.ResponseWriter, r *http.Request) {
		templ := template.Must(template.ParseFiles("index.html"))
		users := map[string][]User{
			"Users": {
				{"1", "Daniel", "Soden",,"dsoden09@gmail.com","daniel04soden",""},
			},
		}
		err := templ.Execute(w, users)
		if err != nil {
			fmt.Println("No films in struct")
			return
		}
	}
	http.HandleFunc("/", initPage)
	log.Fatal(http.ListenAndServe(port, nil))
}
