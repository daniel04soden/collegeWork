package games

import (
	"fmt"
	"dice-arcade/internal/games/decorate"
	"dice-arcade/internal/games/legacy"
)

func New(kind string) (Game, error) {
	switch kind {
	case "highlow":
		return HighLow{}, nil
	case "highlow+log":
		h := HighLow{}
		return decorate.WrapLogging(h), nil
	case "highlow+score":
		h:= HighLow{}
		return decorate.WrapScoring(h),nil
	case "highlow+log+score":
		h:= HighLow{}
		return decorate.WrapLogging(decorate.WrapScoring(h)),nil
	case "highlow+score+log":
		h:=HighLow{}
		return decorate.WrapScoring(decorate.WrapLogging(h)),nil
	case "legacy":
		l:=legacy.NewHighLowLegacy()
		return legacy.NewHighLowAdapter(l), nil
	case "legacy+log":
		l:=legacy.NewHighLowLegacy()
		return decorate.WrapLogging(legacy.NewHighLowAdapter(l)), nil
	case "pig":
		return Pig{}, nil
	case "pig+log":
		p := Pig{}
		return decorate.WrapLogging(p), nil
	case "pig+score":
		p:= Pig{}
		return decorate.WrapScoring(p),nil
	default:
		return nil, fmt.Errorf("unknown game: %s", kind)
	}
}
