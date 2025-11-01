package com.example.assignment1.viewmodels

import androidx.lifecycle.ViewModel
import com.example.assignment1.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel: ViewModel() {
    var currentUser: User?=null
    private set

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _height = MutableStateFlow("")
    val height: StateFlow<String> = _height

   private val _weight = MutableStateFlow("")
    val weight: StateFlow<String> = _weight

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val registeredUsers = mutableListOf<User>()

    fun validatePass(pass:String):Boolean{
        val restrictions: Boolean =
                    pass.contains("[A-Z]".toRegex())
                    && pass.contains("[a-z]".toRegex())
                    && pass.contains("[0-9]".toRegex())
        return pass.length >= 8 && restrictions
    }

    fun checkPasswordsForCreation(passOne:String, passTwo:String): Boolean {
        val checkPassOne: Boolean = validatePass(passOne)
        val checkPassTwo: Boolean = validatePass(passTwo)

        return (passTwo == passOne) && (checkPassOne && checkPassTwo)
    }

    fun checkEmail(email: String): Boolean {
       return email.contains("@") && email.contains(".")
    }

    fun verifyDetails(
        name: String, age: Int, email: String, height: Double, weight: Double,
        password: String,confirmPass:String
    ): Boolean {
        val passwordCheck = checkPasswordsForCreation(password,confirmPass)
        val emailCheck = checkEmail(email)
        return passwordCheck && emailCheck
    }

    fun addUser(
        name: String, age: Int, email: String, height: Double, weight: Double,
        password: String,confirmPass:String
    ): User? {
        if (
            verifyDetails(name, age, email, height, weight, password,confirmPass)
        ){
            val newUser = User(name, age, email, password, height, weight)
            println(newUser.toString())
            registeredUsers.add(newUser)
            println(registeredUsers.toString())
            return newUser

        }else{
            println("Passwords do not match")
            return null
        }
    }
}