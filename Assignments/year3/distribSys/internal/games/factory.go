package games

import (
	"fmt"
)

func New(kind string) (Game, error) {
	baseGame:= NewStandardGame()
	switch kind {
	case "standard":
		return baseGame, nil
	case "standard+logging":
		return NewLoggingGame(baseGame),nil
	default:
		return nil, fmt.Errorf("unknown game kind %s", kind)
	}
}
