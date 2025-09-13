var name:String = "Daniel" // Mutable state can be changed
val pi:Double = 3.14 // Immutable state ie const

fun greet(name:String, age:Int, weight:Double){
    println("Hi $name, you are $age years old and $weight kg")
}

fun operateOnNumber(number:Int, operation:(Int)->Int): Int{
    return operation(number)
}

fun createMultiplier(factor:Int): (Int)->Int{
    return {number -> number * factor}
}



fun main(args: Array<String>) {
    println("Hello there $name")
    println("This variable cannot be changed $pi")
    val age = readlnOrNull()
    if (age == null || age == ""){
        println("Please enter an age next time")
    }else{
        when(age.toInt()){
            1 -> println("wtf yourenot 1")
            2 -> println("wtf yourenot 2")
            else -> println("Um")
        }
    }
    for (i in 1..5){
    greet("Daniel",i,78.8)


    }
    val mutableItems = mutableListOf(1,2,3,4,5)
    for ( i in  mutableItems){
        println(i)
    }

    val myLambda:(Int,Int)-> Int= {a,b -> a+b}
    val y = myLambda(2,3)
    println(y)
    val items = listOf(1,2,3,4,5,6,7,8)
    val evens = items.filter{it %2 == 0}
    println(evens)
    val result = operateOnNumber(5,{it*5})
    println(result)
    val res = createMultiplier(4)
    println(res(10))



}
