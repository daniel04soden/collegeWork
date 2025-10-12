package legacy

import (
	"dice-arcade/internal/dice"
)

type HighLowLegacy struct{
	Roll func() int
}

func NewHighLowLegacy() *HighLowLegacy{
	return &HighLowLegacy{Roll: dice.D6}
}

func (h *HighLowLegacy) RunOnce() (int,bool){
	n:=h.Roll()
	return n,n>=4
}
