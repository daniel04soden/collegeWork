package main

import (
	"fmt"
)

func main(){
	fmt.Print("Enter your name:  ")
	var name string
	fmt.Scanln(&name)
	fmt.Println("Welcome to the Daniel Spelling bee game, ",name)
}
