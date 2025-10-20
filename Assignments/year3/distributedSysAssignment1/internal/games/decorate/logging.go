package decorate

type SpellingGame interface {
	RunGame() int
}

type Logging struct {
	Inner Result
}
