package games

import (
	"fmt"
	"slices"
	"strings"

	"distribSys/internal/bee"
	"distribSys/internal/data"
	"distribSys/internal/entity"
)

type Standard struct {
	words        data.WordDictionary
	wordsGuessed []string
	points       int
	pangram      *entity.Pangram
	targetScore  int
}
const dictPath string = "./internal/data/words_dictionary.json"

func NewStandardGame() *Standard {
	words := data.GetValidWords(dictPath)
	pangram := bee.GeneratePangram(dictPath)

	return &Standard{
		words:        words,
		wordsGuessed: make([]string, 0),
		points:       0,
		pangram:      pangram,
		targetScore:  25,
	}
}

func (s *Standard) Type() string { return "Standard" }

func (s *Standard) GetState() (string, int) {
	return bee.DisplayPangram(s.pangram), s.points
}

func (s *Standard) ProcessGuess(guess string) (int, []string, string, bool) {
	guess = strings.ToLower(guess)
	if slices.Contains(s.wordsGuessed, guess) || len(guess) < 4 {
		return s.points, s.wordsGuessed, "Invalid guess", s.points >= s.targetScore
	}

	newPoints := bee.ProcessEntry(guess, s.pangram, s.words)

	statusMessage := ""
	if newPoints > 0 {
		s.wordsGuessed = append(s.wordsGuessed, guess)
		s.points += newPoints

		if newPoints > 7 {
			statusMessage = fmt.Sprintf("Correct! Pangram! (+%d points)", newPoints)
		} else {
			statusMessage = fmt.Sprintf("Correct! (+%d points)", newPoints)
		}

	} else {
		statusMessage = "Invalid word or word not found."
	}

	isGameOver := s.points >= s.targetScore

	return s.points, s.wordsGuessed, statusMessage, isGameOver
}
