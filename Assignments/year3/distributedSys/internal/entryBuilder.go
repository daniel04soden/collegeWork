package main

import (

"fmt"

)

type EntryBuilder interface{
	SetLetters(letters string) EntryBuilder
	SetLength(letters string) EntryBuilder
	Build() *Entry
}

type entryBuilder struct{
	entry *Entry
}

type entryDirector struct{
	entryBuilder entryBuilder
}

func (eD *entryDirector) ConstructEntry(letters string) *Entry{
	eD.entryBuilder.SetLetters(letters)
	eD.entryBuilder.SetLength(letters)
	
	return eD.entryBuilder.Build()
}

func NewEntryBuilder() EntryBuilder{
	return &entryBuilder{
		entry:&Entry{},
	}
}

func (eb *entryBuilder) SetLetters(letters string) EntryBuilder{
	eb.entry.letters = letters
	return eb
}

func (eb *entryBuilder) SetLength(letters string) EntryBuilder{
	lenLetters := len(letters)
	if lenLetters < 4{
	  fmt.Println("Word too short try again next time")
	  return nil
	}else{
	eb.entry.length = lenLetters  
	return eb
	}
}

func (eb *entryBuilder) Build() *Entry{
	return eb.entry
}
