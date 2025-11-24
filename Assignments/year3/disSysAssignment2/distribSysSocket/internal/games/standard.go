package games

import (
	"fmt"
	"log"
	"slices"

	"distribSys/internal/bee"
	"distribSys/internal/data"
	"distribSys/internal/entity"
)

func getEntry(p *entity.Pangram) (response string) {
	fmt.Print("Enter a word: ")
	_, err := fmt.Scanln(&response)
	if err != nil {
		log.Fatal(err)
	}

	return response
}

type Standard struct{}

func (Standard) Type() string { return "Standard" }

func (Standard) Play() (int, []string) {
	fmt.Println("Welcome to the Spelling Bee Game!")
	words := data.GetValidWords("../../internal/data/words_dictionary.json")
	var wordsGuessed []string
	points := 0
	pangram := bee.GeneratePangram("../../internal/data/words_dictionary.json")
	fmt.Println(pangram.Letters)
	fmt.Println(bee.DisplayPangram(pangram))
	for points <= 25 {
		guess := getEntry(pangram)
		points += bee.ProcessEntry(guess, pangram, words)
		if slices.Contains(wordsGuessed, guess) {
			fmt.Println("You already guessed that word!")
			continue
		} else {
			wordsGuessed = append(wordsGuessed, guess)
		}
	}

	return points, wordsGuessed
}
