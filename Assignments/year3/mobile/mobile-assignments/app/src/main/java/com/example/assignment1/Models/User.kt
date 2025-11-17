package com.example.assignment1.Models

class User
{
    var id:Int
    var name:String
    var age:Int
    val gender: String // true for female, false for male
    var email:String
    var password:String
    var weight: Double
    var height: Double
    var loseWeight: String
    var caloriesPerDay: Double

    constructor(
        id:Int,
        name:String,
        age:Int,
        gender: String, // true for female, false for male
        email:String,
        password:String,
        weight: Double,
        height: Double,
        loseWeight: String,
    ){
        this.id = id
        this.name = name
        this.age = age
        this.gender = gender // M/F
        this.email = email
        this.password = password
        this.weight = weight
        this.height = height
        this.loseWeight = loseWeight // Y/N
        this.caloriesPerDay = calculateCaloriesPerDay()
    }
    fun calculateCaloriesPerDay(): Double {
        var res:Double = 0.0
        /*
        Calculation for calories found below:
            Male: 10 × weight + 6.25 × height - 5 × age + 5
            Female: 10 × weight + 6.25 × height - 5 × age - 161
          - We will simply add/take 200 depending on if they are looking to lose weight or not
        * */
        res = if (this.gender.lowercase() == "female") { // female
            10 * this.weight + 6.25 * this.height - 5 * this.age - 161
        }else{ // male
            10 * this.weight + 6.25 * this.height - 5 * this.age + 5
        }

        if (this.loseWeight == "Y") {
            res -= 200
        }else{
            res += 200
        }
        return res
    }
}