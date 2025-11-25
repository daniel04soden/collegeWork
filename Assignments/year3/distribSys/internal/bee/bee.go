package bee

// Package bee provides general logical functions for the spelling bee game.

import (
	"fmt"
	"math/rand"
	"strings"

	"distribSys/internal/data"
	"distribSys/internal/entity"
)

func ScrambleLetters(input string) (output string) {
	shuff := []rune(input)
	rand.Shuffle(len(shuff), func(i, j int) {
		shuff[i], shuff[j] = shuff[j], shuff[i]
	})
	output = string(shuff)
	return output
}

func DisplayPangram(p *entity.Pangram) (res string) {
	otherLetters := ""
	for _, l := range p.Letters {
		if string(l) != p.MiddleVal {
			otherLetters += string(l)
		}
	}

	scrambledOthers := ScrambleLetters(otherLetters)

	n := len(p.Letters)

	for i := range n {
		if i == n/2 {
			res += fmt.Sprintf(" [%s] ", p.MiddleVal)
		} else {
			if i < n/2 {
				res += fmt.Sprintf(" %s ", string(scrambledOthers[i]))
			} else {
				res += fmt.Sprintf(" %s ", string(scrambledOthers[i-1]))
			}
		}
	}
	return res
}

func getPoints(input string, possibleLetters string) (points int) {
	if len(input) >= len(possibleLetters) && strings.Contains(input, possibleLetters) {
		return len(input) + 7
	} else {
		return len(input)
	}
}

func ProcessEntry(userInput string, p *entity.Pangram, validWords data.WordDictionary) (points int) {
	entryBuilder := entity.NewEntryBuilder()
	entryDirector := &entity.EntryDirector{EntryBuilder: entryBuilder}
	entry := entryDirector.ConstructEntry(userInput, *p, validWords)

	if entry != nil {
		points = getPoints(entry.Letters, p.Letters)
	} else {
		points = 0
	}
	return
}

func GeneratePangram(dictPath string) (p *entity.Pangram) {
	pangramStr := data.GetRandomPangram(dictPath)
	pangramBuilder := entity.NewPangramBuilder()
	pangramDirector := &entity.PangramDirector{PangramBuilder: pangramBuilder}
	pangram := pangramDirector.ConstructPangram(pangramStr)

	return pangram
}
