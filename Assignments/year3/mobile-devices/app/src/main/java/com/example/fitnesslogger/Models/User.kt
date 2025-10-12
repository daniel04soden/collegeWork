package com.example.fitnesslogger.Models
import android.R.attr.name
import org.mindrot.jbcrypt.BCrypt

class User{
    final val id: Int
    var firstName:String
    var lastName: String
    var userName:String
    var password: String
    var age:Int
    var email: String
    var passPhrase: String

    constructor(
        id:Int,
        firstName:String,
        lastName:String,
        userName:String,
        password:String,
        age:Int,
        email:String,
        passPhrase: String,
    ){
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName
        this.id = id
        this.password = BCrypt.hashpw(password, BCrypt.gensalt())
        this.age = age
        this.email = email
        this.passPhrase = passPhrase
    }
    fun changePassword(originalPassEntry:String,newPassword:String): Boolean {
        if (BCrypt.checkpw(originalPassEntry,this.password)){
            this.password = newPassword
            return true
        }
        return false
    }
}