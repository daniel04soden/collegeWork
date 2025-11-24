package decorate

import (
	"log"
)

type Game interface {
	Type() string
	Play() (int, []string)
}

type Logging struct {
	Inner Game
}

func (d Logging) Type() string { return d.Inner.Type() }

func (d Logging) Play() (int, []string) {
	point, guess := d.Inner.Play()
	log.Printf("[LOG] %s → Total Points %d", d.Type(), point)
	for i := range guess {
		log.Printf("[LOG] Guess No. %d → %s", i, guess[i])
	}
	return point, guess
}

func WrapLogging(g Game) Game { return Logging{g} }
