package main

import (
	"fmt"
)

func main() {
	builder := NewPersonBuilder()
	director := &PersonDirector{builder:builder}

	myPerson := director.ConstructPerson(21, "Daniel", 1.8)
	fmt.Println(myPerson.name)
	fmt.Println(myPerson.age)
	fmt.Println(myPerson.height)

	var myStr string = introduceSelf(*myPerson)
	fmt.Println(myStr)
}
