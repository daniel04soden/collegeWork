package main

import (
	"distributedSysAssignment1/entity"
	"fmt"
	"math/rand"
	"slices"
)

func scrambleLetters(input string) (output string) {
	shuff := []rune(input)
	rand.Shuffle(len(shuff), func(i, j int) {
		shuff[i], shuff[j] = shuff[j], shuff[i]
	})
	output = string(shuff)
	return output
}

func gameLoop(numGames int) {
	var points int
	points = 0
	var validGuessedWords []string
	genWord := scrambleLetters("autocrat")
	pangramBuilder := entity.NewPangramBuilder()
	pangramDirector := &entity.PangramDirector{PangramBuilder: pangramBuilder}
	pangram := pangramDirector.ConstructPangram(genWord)
	fmt.Printf("Spelling Bee Welcome!\n")
	for range numGames {
		fmt.Printf("Pangram: %s\n", genWord)
		fmt.Printf("Middle value %v", pangram.MiddleVal)
		fmt.Printf("Enter your word: ")
		var userIn string
		fmt.Scanln(&userIn)
		if !slices.Contains(validGuessedWords, userIn) {
			entryBuilder := entity.NewEntryBuilder()
			entryDirector := &entity.EntryDirector{EntryBuilder: entryBuilder}
			entry := entryDirector.ConstructEntry(userIn, *pangram)
			if entry.Length != 0 {
				validGuessedWords = append(validGuessedWords, entry.Letters)
			}
			point := entity.CalcScore(*entry, "autocracy")
			points += point

		} else {
			fmt.Printf("%s has already been guessed!\n", userIn)
		}
	}
	fmt.Printf("Final score:%d \t Great job\n", points)
	fmt.Print("Words Guessed: [")
	for i := range len(validGuessedWords) {
		fmt.Printf(" %s, ", validGuessedWords[i])
	}
	fmt.Print("]\n")
}

func main() {
	gameLoop(3)
}
