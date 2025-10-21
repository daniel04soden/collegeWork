package game

import (
	"distributedSysAssignment1/internal/data"
	"distributedSysAssignment1/internal/entity"
	"fmt"
	"math/rand"
	"slices"
)

type Result struct {
	GamesPlayed int
	GameStats   []game
	UserName    string
}

type game struct {
	Points      int
	WordsGussed []string
}

func formatPangram(pangram string, p *entity.Pangram) (res string) {
	pangram = scrambleLetters(pangram)
	n := len(pangram)
	for i := range n {
		if i == n/2 {
			res += fmt.Sprintf(" [" + string(p.MiddleVal) + "] ")
		} else {
			if string(pangram[i]) != p.MiddleVal {
				res += fmt.Sprintf(" " + string(pangram[i]) + " ")
			} else {
				continue
			}
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

func generatePangram(dictPath string) (p *entity.Pangram, output string) {
	pangramStr := data.GetRandomPangram(dictPath)
	pangramBuilder := entity.NewPangramBuilder()
	pangramDirector := &entity.PangramDirector{PangramBuilder: pangramBuilder}
	pangram := pangramDirector.ConstructPangram(pangramStr)

	return pangram, pangramStr
}

func processEntry(userInput string, currentGuesses []string, p *entity.Pangram, dictPath string) (points int) {
	if !(slices.Contains(currentGuesses, userInput)) && len(userInput) >= 4 && data.ValidateWord(userInput, data.GetValidWords(dictPath)) {
		entryBuilder := entity.NewEntryBuilder()
		entryDirector := &entity.EntryDirector{EntryBuilder: entryBuilder}
		entry := entryDirector.ConstructEntry(userInput, *p)
		points = entity.CalcScore(*entry, p.Letters)

	} else {
		fmt.Println("Unable to process entry it must: be unique, include the middle letter, be 4 or more characters long and be a dictionary word")
		points = 0
	}
	return points
}

func Type() (name string) {
	name = "standard game"
	return
}

func RunOnce(dictPath string) int {
	score := 0
	var guessedWords []string

	pGram, displayPangram := generatePangram(dictPath)

	fmt.Printf("Pangram = %s\n", formatPangram(displayPangram, pGram))
	for score < 25 {
		var guess string
		fmt.Printf("Enter your guess:")
		fmt.Scanln(&guess)
		pointsFromGuess := processEntry(guess, guessedWords, pGram, dictPath)
		score += pointsFromGuess
		guessedWords = append(guessedWords, guess)
	}
	return score
}

func PlayOnce(timesToRun int, userName string, dictPath string) (finalScore int) {
	games := []game{}
	for range timesToRun {
		curGame := game{0, []string{}}
		pGram, displayPangram := generatePangram(dictPath)

		fmt.Printf("Pangram = %s\n", formatPangram(displayPangram, pGram))
		for curGame.Points < 25 {
			var guess string
			fmt.Printf("Enter your guess:")
			fmt.Scanln(&guess)
			pointsFromGuess := processEntry(guess, curGame.WordsGussed, pGram, dictPath)
			curGame.Points += pointsFromGuess
			curGame.WordsGussed = append(curGame.WordsGussed, guess)
		}

		games = append(games, curGame)
	}
	res := Result{len(games), games, userName}

	finalScore = 0
	for i := range res.GameStats {
		finalScore += res.GameStats[i].Points
	}
	return
}
