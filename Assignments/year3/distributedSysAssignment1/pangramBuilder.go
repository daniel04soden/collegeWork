package main


import (
"fmt"
)

type PangramBuilder interface{
	SetLetters(letters string) PangramBuilder
	SetLength(letters string) PangramBuilder
	Build() *Pangram
}

type pangramBuilder struct{
	pangram *Pangram
}

type PangramDirector struct{
	pangramBuilder PangramBuilder
}



func (pD *PangramDirector) ConstructPangram(letters string) *Pangram{
	pD.pangramBuilder.SetLetters(letters)
	pD.pangramBuilder.SetLength(letters)
	
	return pD.pangramBuilder.Build()
}

func NewPangramBuilder() PangramBuilder{
	return &pangramBuilder{
		pangram:&Pangram{},
	}
}

func (pb *pangramBuilder) SetLetters(letters string) PangramBuilder{
	pb.pangram.letters = letters
	return pb
}

func (pb *pangramBuilder) SetLength(letters string) PangramBuilder{
	lenLetters := len(letters)
	if lenLetters < 4{
	  fmt.Println("Word too short try again next time")
	  return nil
	}else{
	pb.pangram.length = lenLetters  
	return pb
	}
}

func (pb *pangramBuilder) setMiddleVal(letters string) PangramBuilder{
	res := []rune(letters)
	pb.pangram.middleVal =  res[pb.pangram.length/2]
	fmt.Println(pb.pangram.middleVal)
	return pb
}

func (pb *pangramBuilder) Build() *Pangram{
	return pb.pangram
}
