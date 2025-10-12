package legacy

import (
	"fmt"
)

type Game interface {
	Name() string
	PlayOnce() string
}

type HighLowAdapter struct{ 
	legacy *HighLowLegacy
}


func NewHighLowAdapter(l *HighLowLegacy) *HighLowAdapter{
	return &HighLowAdapter{legacy: l}
}

func (a *HighLowAdapter) Name() string{
	return "highlow_legacy"
}

func (a *HighLowAdapter) PlayOnce() string{
	roll,win:= a.legacy.RunOnce()
	if win{
		return fmt.Sprintf("Highlow(legacy): rolled %d -> WIN",roll)
	}
	return fmt.Sprintf("Highlow(legacy): rolled %d -> LOSE",roll)
}
