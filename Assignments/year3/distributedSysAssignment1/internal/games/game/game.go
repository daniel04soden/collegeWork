package game

type Game interface {
	Type() string
	PlayOnce(dictPath string) int 
}
