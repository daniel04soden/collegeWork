package games

type Game interface {
	Type() string
	Play() (int, []string)
}
