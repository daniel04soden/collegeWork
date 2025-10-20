package game

import (
	"distributedSysAssignment1/internal/data"
	"distributedSysAssignment1/internal/entity"
	"fmt"
	"math/rand"
	"slices"
)

type Result struct {
	gamesPlayed int
	gameStats   []Game
	userName    string
}

type Game struct {
	points      int
	wordsGussed []string
}

func formatPangram(pangram string) (res string) {
	n := len(pangram)
	for i := range n {
		if i == n/2 {
			res += fmt.Sprintf(" [" + string(pangram[i]) + "] ") // Quite manual but needed for middle value importance
		} else {
			res += fmt.Sprintf(" " + string(pangram[i]) + " ")
		}
	}
	return res
}

func scrambleLetters(input string) (output string) {
	/*
	  Scrambling the letters so that users cant instantly guess the pangram
	*/
	shuff := []rune(input)
	rand.Shuffle(len(shuff), func(i, j int) {
		shuff[i], shuff[j] = shuff[j], shuff[i]
	})
	output = string(shuff)
	return output
}

func generatePangram() (p *entity.Pangram, output string) {
	pangramStr := data.GetRandomPangram("../data/words_dictionary.json")
	pangramBuilder := entity.NewPangramBuilder()
	pangramDirector := &entity.PangramDirector{PangramBuilder: pangramBuilder}
	pangram := pangramDirector.ConstructPangram(pangramStr)

	return pangram, scrambleLetters(pangramStr)
}

func processEntry(userInput string, currentGuesses []string, p *entity.Pangram) (newCurrentGuesses []string, points int) {
	if !(slices.Contains(currentGuesses, userInput)) && len(userInput) >= 1 {
		entryBuilder := entity.NewEntryBuilder()
		entryDirector := &entity.EntryDirector{EntryBuilder: entryBuilder}
		entry := entryDirector.ConstructEntry(userInput, *p)
		newCurrentGuesses = append(currentGuesses, entry.Letters)

		points = entity.CalcScore(*entry, p.Letters)
	} else {
		newCurrentGuesses = currentGuesses
		points = 0
	}
	return newCurrentGuesses, points
}

func RunGame(timesToRun int, userName string) (res Result) {
	games := []Game{}
	for range timesToRun {
		var curGame Game = Game{0, []string{}}
		pGram, displayPangram := generatePangram()
		fmt.Printf("Pangram = %s\n", formatPangram(pGram.Letters))
		fmt.Printf("Enter your guess:")

		games = append(games, curGame)
	}
	return Result{timesToRun, games, userName}
}

func main() {
	var name string
	fmt.Println("Welcome to the spelling bee!")
	fmt.Printf("What is your name?  ")
	fmt.Scanln(&name)
	fmt.Println("\n How many games would you like to play?")
	var timesToRun int
	finalRes := RunGame(timesToRun, name)
	fmt.Printf("Final Result: Games Played = %d", finalRes.gamesPlayed)
}
