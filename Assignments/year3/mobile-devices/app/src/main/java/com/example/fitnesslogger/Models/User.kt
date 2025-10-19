package com.example.fitnesslogger.Models
import android.R.attr.name
import android.os.Build
import androidx.annotation.RequiresApi
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate
import java.time.Period
import java.util.Date

class User{
    final val id: Int
    var firstName:String
    var dateOfBirth: LocalDate
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
        dateOfBirth: LocalDate
    ){
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName
        this.id = id
        this.password = BCrypt.hashpw(password, BCrypt.gensalt())
        this.age = age
        this.email = email
        this.passPhrase = passPhrase
        this.dateOfBirth = dateOfBirth
    }
    fun changePassword(originalPassEntry:String,newPassword:String): Boolean {
        if (BCrypt.checkpw(originalPassEntry,this.password)){
            this.password = newPassword
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAge(year: Int, month: Int, dayOfMonth: Int): Period? {
        return Period.between(dateOfBirth, LocalDate.now())
    }
}