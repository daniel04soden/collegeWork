package games

import (
	"dice-arcade/internal/dice"
	"fmt"
)

type DoubleDice struct{
}

func (DoubleDice) Name() string {
	return "doubledice - win with greater than 8"
}

func (DoubleDice) PlayOnce() string {
	n := dice.D6()
	var m int
	fmt.Print("Enter a number")
	fmt.Scanln(&m)

	res := n+m
	if n+m >= 8{
		return fmt.Sprintf("DoubleDice: rolled %d and chose %d  result is %d→ WIN", n,m,res)
	}
	return fmt.Sprintf("DoubleDice: rolled %d and chose %d  result is %d→ Lose", n,m,res)
}
