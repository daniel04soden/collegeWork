# Singleton Pattern
```go
// single.go
package main

import (
	"fmt"
	"sync"
)


type singleton struct{
value string
}

var instance *singleton
var once sync.Once

func getInstance() *singleton{
	once.Do(func(){
		instance = &singleton{value:"I am the only instance"}	
	})
return instance
}
```