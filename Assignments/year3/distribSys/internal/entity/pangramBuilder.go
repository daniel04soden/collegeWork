package entity

import (
	"fmt"
	"strings"
)

type PangramBuilder interface {
	SetLetters(letters string) PangramBuilder
	SetLength(letters string) PangramBuilder
	SetMiddleVal(letters string) PangramBuilder
	Build() *Pangram
}

type pangramBuilder struct {
	pangram *Pangram
}

type PangramDirector struct {
	PangramBuilder PangramBuilder
}

func (pD *PangramDirector) ConstructPangram(letters string) *Pangram {
	if pD.PangramBuilder == nil { // If ever returned as nil with various setters, not constructed
		fmt.Println("Invalid pangram, try play again & re-read the rules")
		return nil
	}
	pD.PangramBuilder.SetLetters(letters)
	pD.PangramBuilder.SetLength(letters)
	pD.PangramBuilder.SetMiddleVal(letters)

	return pD.PangramBuilder.Build()
}

func NewPangramBuilder() PangramBuilder {
	return &pangramBuilder{
		pangram: &Pangram{},
	}
}

func uniqueLetters(potentialPangram string) (res bool) {
	checkStr := make(map[rune]struct{})
	for _, char := range potentialPangram {
		checkStr[char] = struct{}{}
	}
	return len(checkStr) == len(potentialPangram)
}

func verifyPangram(letters string) bool {
	return len(letters) == 7 && !strings.Contains(strings.ToLower(letters), "s") && uniqueLetters(letters)
}

func (pb *pangramBuilder) SetLetters(letters string) PangramBuilder {
	if !verifyPangram(letters) {
		fmt.Printf("Invalid pangram must contain letter s\n")
		return nil
	} else {
		pb.pangram.Letters = letters
		return pb
	}
}

func (pb *pangramBuilder) SetLength(letters string) PangramBuilder {
	lenLetters := len(letters)
	if lenLetters < 4 {
		fmt.Println("Word too short try again next time")
		return nil
	} else {
		pb.pangram.Length = lenLetters
		return pb
	}
}

func (pb *pangramBuilder) SetMiddleVal(letters string) PangramBuilder {
	if len(letters) < 4 {
		fmt.Println("Word too short try again next time")
		return nil
	} else {
		res := []rune(letters)
		length := len(res)
		middle := (length / 2) - 1 // works well for both odd and even
		pb.pangram.MiddleVal = string(res[middle])
		return pb
	}
}

func (pb *pangramBuilder) Build() *Pangram {
	return pb.pangram
}
