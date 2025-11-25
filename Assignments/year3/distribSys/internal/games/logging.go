package games

import (
	"log"
)

type LoggingGame struct {
	Game
}

func NewLoggingGame(g Game) Game {
	log.Printf("Game of type %s created with logging enabled.", g.Type())
	return &LoggingGame{Game: g}
}

func (g *LoggingGame) Type() string {
	return g.Game.Type() + "+logging"
}

func (g *LoggingGame) ProcessGuess(guess string) (int, []string, string, bool) {
	log.Printf("Processing guess: '%s'", guess)

	score, words, msg, done := g.Game.ProcessGuess(guess)

	log.Printf("Guess result: Score=%d, Status='%s', GameOver=%t", score, msg, done)
	return score, words, msg, done
}

func (g *LoggingGame) GetState() (string, int) {
	letters, score := g.Game.GetState()
	log.Printf("Game state requested: Score=%d", score)
	return letters, score
}
