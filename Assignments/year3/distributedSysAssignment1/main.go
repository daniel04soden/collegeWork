package main
import (
	"fmt"
)

func main(){
	fmt.Println("dd")
	builder := NewEntryBuilder()
	director := &(EntryDirector{builder})
	
	myEntry := director.ConstructEntry("hello")
	fmt.Println(myEntry.length)
	fmt.Println(myEntry.letters)
}
