package entity

import (
	"fmt"
)

type EntryBuilder interface {
	SetLetters(letters string) EntryBuilder
	SetLength(letters string) EntryBuilder
	Build() *Entry
}

type entryBuilder struct {
	entry *Entry
}

type EntryDirector struct {
	EntryBuilder EntryBuilder
}

func (eD *EntryDirector) ConstructEntry(letters string) *Entry {
	if eD.EntryBuilder == nil {
		return nil
	}

	eD.EntryBuilder.SetLetters(letters)
	eD.EntryBuilder.SetLength(letters)
	return eD.EntryBuilder.Build()
}

func NewEntryBuilder() EntryBuilder {
	return &entryBuilder{
		entry: &Entry{},
	}
}

func (eb *entryBuilder) SetLetters(letters string) EntryBuilder {
	eb.entry.Letters = letters
	return eb
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
