package decorate
import (
	"strings"
	"fmt"
)

type Scoring struct {
	Inner Game
	Points int
}

func (d *Scoring) Name() string{
	return d.Inner.Name()
}

func (d *Scoring) PlayOnce() string{
	out:=d.Inner.PlayOnce()
	if strings.Contains(out,"WIN") || strings.Contains(out, "+points"){
		d.Points += 1
		s:=fmt.Sprintf("You gained 1 point: Score= %d",d.Points)
		fmt.Println(s)
	}
	
	return out
}

func WrapScoring (g Game) *Scoring {return &Scoring{Inner:g}}
