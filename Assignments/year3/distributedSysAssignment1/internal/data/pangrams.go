package data

import (
	"encoding/json"
	"io"
	"log"
	"math/rand/v2"
	"os"
	"strings"
)

type WordDictionary struct {
	ValidWords []map[string]int `json:validwords`
}

type ValidEngWord struct {
	Word string
}

func uniqueLetters(potentialPangram string) (res bool) {
	checkStr := make(map[rune]struct{})
	for _, char := range potentialPangram {
		checkStr[char] = struct{}{}
	}
	return len(checkStr) == len(potentialPangram)
}

func checkIsPangram(wordIn string) (isPangram bool) {
	return len(wordIn) == 7 && !strings.Contains(strings.ToLower(wordIn), "s") && uniqueLetters(wordIn)
}

func GetValidWords(wordDictFile string) (res WordDictionary) {
	jsonFile, err := os.Open(wordDictFile)
	if err != nil {
		log.Fatal(err)
	}

	defer jsonFile.Close()

	data, err := io.ReadAll(jsonFile)
	if err != nil {
		log.Fatal(err)
	}

	var words WordDictionary
	err = json.Unmarshal(data, &words)
	if err != nil {
		log.Fatal(err)
	}
	return words
}

func ValidateWord(word string, words WordDictionary) bool {
	wordAccess := words.ValidWords[0]
	wordExists := wordAccess[word] == 1
	return wordExists
}

func GetRandomPangram(wordDictFile string) (pangram string) {
	var allPangrams []string

	jsonFile, err := os.Open(wordDictFile)
	if err != nil {
		log.Fatal(err)
	}

	defer jsonFile.Close()

	data, err := io.ReadAll(jsonFile)
	if err != nil {
		log.Fatal(err)
	}

	var words WordDictionary
	err = json.Unmarshal(data, &words)
	if err != nil {
		log.Fatal(err)
	}

	wordMap := words.ValidWords[0]

	for word := range wordMap {
		if checkIsPangram(word) {
			allPangrams = append(allPangrams, word)
		}
	}

	randomIndex := rand.IntN(len(allPangrams))
	return allPangrams[randomIndex]
}
