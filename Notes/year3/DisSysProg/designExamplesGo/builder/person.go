package main

import (
	"fmt"
)

type Person struct{
	name string
	age int
	height float32
}

func introduceSelf(p Person) string {
	return fmt.Sprintf("Hi my name is %s, I am %d years old and I am %.2f M tall",p.name,p.age,p.height)
}
