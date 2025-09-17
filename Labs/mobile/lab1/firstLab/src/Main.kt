import kotlin.math.max
import kotlin.math.pow

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

val pi:Double = 3.14

fun exerciseOneOne():String{
    val name:String= "Daniel"
    val age: Int = 21;

    val res:String =  "My name is $name, I am $age years old"

    println(res)
    return res
}

fun exerciseOneTwo(radius: Double):Double{
    return pi.pow(radius)
}

fun exerciseTwoOne(num: Int?):Boolean{
   return num!=null && num % 2 == 0
}

fun exerciseTwoTwo(age:Int){
    var res:String = ""
    res = when{
        age < 12 -> "Child"
        age in 12..18 -> "Teenager"
        else -> "Adult"
    }
    println(res)
}

fun exerciseThreeOne(){
    for (i in 1..20){
        println(i)
    }
}
fun exerciseThreeTwo(){
    for (i in 1..10){
        val multiple:Int = i*10
        println("10x$i= $multiple")
    }
}

fun exerciseThreeThree(n:Int){
    var res:Int = 1
    for (i in 1..n+1){
       res *= i
    }
    println(res)
}

fun exerciseFourOne(name:String){
    println("Hello $name")
}

fun exerciseFourTwo(x:Int,y:Int):Int{
    return x+y
}

fun exerciseFourThree(num1:Int,num2:Int,num3:Int):Int{
    return max(max(num1,num2),num3)
}

fun exerciseFourFour(a:Int,b:Int): Int {
    var ans = a
    for (i in 1..b){
        ans *= ans
    }
    return ans
}

fun exerciseFiveOne(){
    val list = mutableListOf(1,2,3,4,5)
    for (i in 6..10){
        list+i
    }
    println(list)

    for (item in list){
        print(item)
    }
}
fun exerciseFiveTwo(){
    val nameList = mutableListOf("Dan","Sean","Aoife")
    for (name in nameList){
        print(name)
    }
}
fun exerciseFiveThree(){
    val nums = mutableListOf(1,2,3,4,5,6,7,8,9)
    for (num in nums){
       if (num%2 == 0) {
           println(num)
       }else{
           continue
       }
    }
}

fun exerciseFiveFour(){
    val nums = mutableListOf(1,2,3,4,5,6,7,8,9)
    val size = nums.size
    for (i in size .. 0){
        println(i)
    }
}

fun main() {
     exerciseOneOne()
    exerciseOneTwo(12.2)
     val num: Int? = readlnOrNull()?.toInt()
    exerciseTwoOne(num)
    exerciseTwoTwo(20)
    exerciseTwoTwo(13)
    exerciseTwoTwo(11)
    exerciseThreeOne()
    exerciseThreeTwo()
    exerciseFourOne("Daniel")
    println(exerciseFourTwo(2,3))
    println(exerciseFourThree(2,3,6))
    println(exerciseFourFour(2,2))
    exerciseFiveFour()
}