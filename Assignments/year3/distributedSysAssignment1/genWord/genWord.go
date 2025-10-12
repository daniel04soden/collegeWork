package main
import (
	"fmt"
	"strings"
	"log"
	"os"
	"encoding/json"
	"bytes"
)

var myMap = make(map[string]string)


func isPangram(wordIn string) (bool){
	if ((len(wordIn) == 7) && (strings.Contains(wordIn,"s") == false)){
		return true
	}else{
		return false
	}
}

func main(){
	var w map[string]interface{}

	d,err := (os.ReadFile("words_dictionary.json"))
	if err!=nil{
		log.Fatalf("Failed to open file %v \n",err)
	}

	jsonErr := json.Unmarshal(d,&w)

	if jsonErr!=nil{
		log.Fatalf("Error when parsing json %v \n",err)
	}
	for i :=range len(d){
		fmt.Printf("%v,%d",w[i],w[i])
	}
	
	fmt.Printf("the word is %s",w.letters)
	
}

