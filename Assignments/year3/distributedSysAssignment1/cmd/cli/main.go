package main

import (
	"distributedSysAssignment1/internal/games/game"
	"fmt"
)

func main() {
	const dictPath string = "../../internal/data/words_dictionary.json"
	var name string
	fmt.Println("Welcome to the spelling bee!")
	fmt.Printf("What is your name?  ")
	fmt.Scanln(&name)
	fmt.Println("\n How many games would you like to play?")
	var timesToRun int
	fmt.Scanln(&timesToRun)
	finalScore := game.RunGame(timesToRun, name, dictPath)
	fmt.Println(name)
	fmt.Printf("%d\n", timesToRun)
	fmt.Printf("Final Score = %d\n", finalScore)
}
