package games

type Game interface {
	Type() string
	
	ProcessGuess(guess string) (int, []string, string, bool) 

    GetState() (string, int) 
}
