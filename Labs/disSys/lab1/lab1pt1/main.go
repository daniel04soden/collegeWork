package lab1

import (
	f "fmt"
	convers "strconv"
)

func main() {
	var message string = "Hello world!"
	f.Println(message)
	f.Println(greetMe("Daniel"))
	vars()
	num := 123
	num *=num

	f.Println(num)
	var u uint = 7
	f.Println(u)
	var p float32 = 22.7
	f.Println(p)

	numbers := []int{0,0,0,0,0}
	for i := 0; i< len(numbers); i++{
		f.Print(numbers[i])
	}
	f.Println()
	for i := range numbers{
		f.Print(numbers[i])
	}
	f.Println()

	sliced := []int{2,3,4}
	for i, val :=range sliced {
		f.Println("At index ",i," val is ", val)
	}
	var secondNum string = "224"

	thirdNum,_ := convers.Atoi(secondNum)
	f.Println(thirdNum+1)

	var gg int = 12

	if gg == 3 || gg <= 2{
		f.Println("all good")
	}else if(gg == 12){
		f.Println("why 12")
	}else{
		f.Println("stop")
	}

	var xy int32
	xy = 2

	switch xy {
    case 2:
        f.Println("2 is weird")
    case 3:
        f.Println("i like 3 to be honest")
    case 4:
        f.Println("huh")
    default:
        f.Println("cmon now")
	}

	nm := 0

	for nm < 10{
		f.Println(nm)
		nm++
	}

	// Lambdas
	myVal := func(x int, y int) int{
		return x+y
	}
	f.Println(myVal(2,3))
}

func vars(){
	var msg string
	msg = "Syfm"
	f.Println(msg)

	var x,y int
	x,y = 1,2
	f.Println(x,y)
	x,msg = 2, "Bro what are you on about"
	var(
		l float32 = 3.14
		c string = "a"
	)
	f.Println(l)
	f.Println(c)
}

func constants(){
	const (
		dayOne = "Monday"
		dayTwo = "Tuesday"
		dayThree = "Wednesday"
		dayFour = "Thursday"
		dayFive = "Friday"
		daySix = "Saturday"
		daySeven = "Sunday"
	)
}

func testRoutine(){
	ch :=make(chan string)

	go push("Moe",ch)
	go push("Larry",ch)
	go push("Curly",ch)

	f.Println(<- ch, <- ch ,<- ch)
}

func push(name string, ch chan string){
	msg := "Hey"  + name
	ch <- msg
}

func greetMe(name string) string{
	return "Hello " + name + "!"
}
