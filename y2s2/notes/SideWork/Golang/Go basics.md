```go
package main

  

import (

"fmt"

"math"

)

  

// func add(a int, b int) int {

// return a + b

// }

// func otherAdd(a, b int) int { // Don't need both a int and b int

// return a + b

// }

  

// Multiple return types - errors etc

  

func sqrt(a float64) (float64, error) {

if a < 0 {

return 0, fmt.Errorf("A cannot be a minus")

}

return math.Sqrt(a), nil // Null/undefined, given 2 datatypes

}

  

func main() {

// array vs slices

  

// Array - statically allocated

var x [10]int

  

y := [10]int{1, 2, 4, 5, 5}

fmt.Print(x)

fmt.Print(y)

// Slices - Dynamically allocated

  

z := []int{12, 4, 455}

  

z = append(z, 6)

  

// Maps

  

a := make(map[int]int)

  

a[1] = 2

a[2] = 3

  

// Etc...

  

delete(a, 1)

  

// Loops - just for loops

  

// for i := 0; i < 10; i++ {

  

// }

// var i int = 9

// for i < 10 {

  

// }

// Infinite loops

// for{

// continue

// break

// }

  

// Error notes

  

// a, err := sqrt(2.0)

// if err != nil { // If error occurs

// panic("")

// }

// otherwise

fmt.Println("success")

  

// Structs/Interfaces

// Struct

type Person struct {

name string // Private

age int

username string // Public

}

  

p := Person{name:"Daniel", age:12, username: "daniel04soden"}

fmt.Println(p.age)

p.age = 13

// Interface

type stringer interface{

string() string

}

  

func (p person) string() string{

return "person struct"

}

  

// Pointers

  

x:= 0

b:= &x // Actual pointerA

c:= *b // De reference

  
  

func (p *person) setName(newName string){

p.name = newName // Pointer to actually change it in memory

}

  
  

fmt.Println("Basics of go, need to know this")

}
```