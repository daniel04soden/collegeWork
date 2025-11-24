package games

import (
	"fmt"

	"distribSys/internal/games/decorate"
)

func New(kind string) (Game, error) {
	switch kind {
	case "standard":
		return Standard{}, nil
	case "standard+logging":
		s := Standard{}
		return decorate.WrapLogging(s), nil
	default:
		return nil, fmt.Errorf("unknown game kind %s", kind)
	}
}
