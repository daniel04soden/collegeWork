/*Package entity is used as a means of dealing with the various pseudo objects which I will refer to as entities*/
package entity

import (
	"fmt"
)

type PangramBuilder interface {
	SetLetters(letters string) PangramBuilder
	SetLength(letters string) PangramBuilder
	setMiddleVal(letters string) PangramBuilder
	Build() *Pangram
}

type pangramBuilder struct {
	pangram *Pangram
}

type PangramDirector struct {
	PangramBuilder PangramBuilder
}

func (pD *PangramDirector) ConstructPangram(letters string) *Pangram {
	if pD.PangramBuilder == nil {
		return nil
	}
	pD.PangramBuilder.SetLetters(letters)
	pD.PangramBuilder.SetLength(letters)
	pD.PangramBuilder.setMiddleVal(letters)

	return pD.PangramBuilder.Build()
}

func NewPangramBuilder() PangramBuilder {
	return &pangramBuilder{
		pangram: &Pangram{},
	}
}

func (pb *pangramBuilder) SetLetters(letters string) PangramBuilder {
	pb.pangram.Letters = letters
	return pb
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

func (pb *pangramBuilder) setMiddleVal(letters string) PangramBuilder {
	res := []rune(letters)
	length := len(res)
	pb.pangram.MiddleVal = string(res[length/2])
	return pb
}

func (pb *pangramBuilder) Build() *Pangram {
	return pb.pangram
}
