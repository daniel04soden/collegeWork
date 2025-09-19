package main
import (
	"fmt"
	"time"
)

func main() {
	go displayGoRoutinesInAction("Daniel")
	displayGoRoutinesInAction("Hello main")
}


func displayGoRoutinesInAction(str string){
	for range 3{
		time.Sleep(500*time.Millisecond)
		fmt.Println(str)
	}

}
