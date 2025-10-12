package main

import(
	"strings"
)
type Entry struct{
	letters string
	length int
}

func calcScore(userInput Entry,possibleLetters string)  (score int){
	if userInput.length >= len(possibleLetters) && strings.Contains(userInput.letters,possibleLetters) {
		return userInput.length+7
	}else{
		return userInput.length
	}
}
