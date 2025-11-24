package entity

import (
	"fmt"
	"strings"
	"unicode"

	"distribSys/internal/data"
)

func isLetter(entry string) bool {
	return !strings.ContainsFunc(entry, func(r rune) bool {
		return !unicode.IsLetter(r)
	})
}

type EntryBuilder interface {
	SetLetters(letters string, p Pangram, d data.WordDictionary) EntryBuilder
	SetLength(letters string) EntryBuilder
	Build() *Entry
}

type entryBuilder struct {
	entry *Entry
}

type EntryDirector struct {
	EntryBuilder EntryBuilder
}

func (eD *EntryDirector) ConstructEntry(letters string, p Pangram, w data.WordDictionary) *Entry {
	if eD.EntryBuilder == nil {
		fmt.Println("Invalid entry, try play again & re-read the rules")
		return nil
	} else {
		eD.EntryBuilder.SetLetters(letters, p, w)
		eD.EntryBuilder.SetLength(letters)
		return eD.EntryBuilder.Build()
	}
}

func NewEntryBuilder() EntryBuilder {
	return &entryBuilder{
		entry: &Entry{},
	}
}

func (eb *entryBuilder) SetLetters(letters string, p Pangram, words data.WordDictionary) EntryBuilder {
	if !isLetter(letters) {
		fmt.Println("Invalid entry, must only be alpha chars ,try again next time")
		return nil
	} else if !strings.Contains(strings.ToLower(letters), p.MiddleVal) {
		fmt.Printf("Invalid entry must contain letter %s\n", p.MiddleVal)
		return nil
	} else if !(data.ValidateWord(letters, words)) {
		fmt.Println("Invalid entry, not a real word")
		return nil
	} else {
		eb.entry.Letters = letters
		return eb
	}
}

func (eb *entryBuilder) SetLength(letters string) EntryBuilder {
	lenLetters := len(letters)
	if lenLetters < 4 {
		fmt.Println("Word too short try again next time")
		eb.entry.Length = 0
		return eb
	} else {
		eb.entry.Length = lenLetters
		return eb
	}
}

func (eb *entryBuilder) Build() *Entry {
	return eb.entry
}
