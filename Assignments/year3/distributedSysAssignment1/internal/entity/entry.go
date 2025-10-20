package entity

import (
	"strings"
)

type Entry struct {
	Letters string
	Length  int
}

func CalcScore(userInput Entry, possibleLetters string) (score int) {
	if userInput.Length >= len(possibleLetters) && strings.Contains(userInput.Letters, possibleLetters) {
		return userInput.Length + 7
	} else {
		return userInput.Length
	}
}
