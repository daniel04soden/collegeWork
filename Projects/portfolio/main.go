package main

import (
	"fmt"
	"log"
	"net/http"
	"text/template"
)

type Film struct {
	Title    string
	Duration float64
}

func main() {
	var port = ":3000"
	fmt.Println("Sever started on port ", port)
	initPage := func(w http.ResponseWriter, r *http.Request) {
		templ := template.Must(template.ParseFiles("index.html"))
		films := map[string][]Film{
			"Films": {
				{Title: "Jurassic World", Duration: 23.8},
				{Title: "Jurassic Park", Duration: 23.8},
				{Title: "Fallen Kingdom", Duration: 23.8},
			},
		}
		err := templ.Execute(w, films)
		if err != nil {
			fmt.Println("No films in struct")
			return
		}
	}
	http.HandleFunc("/", initPage)
	log.Fatal(http.ListenAndServe(port, nil))
}
