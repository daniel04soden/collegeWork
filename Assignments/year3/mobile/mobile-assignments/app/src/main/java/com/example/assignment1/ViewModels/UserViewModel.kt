package com.example.assignment1.ViewModels
import androidx.lifecycle.ViewModel
import com.example.assignment1.Models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel: ViewModel(){
    private val users = mutableListOf<User>()

    // Login info
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    // Signup info

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age

    fun onAgeChange(newAge: String) {
        _age.value = newAge
    }

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender
    fun onGenderChange(newGender: String) {
        _gender.value = newGender
    }

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    private val _confirmPassword = MutableStateFlow("")
    val confPswd: StateFlow<String> = _confirmPassword

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    private val _weight = MutableStateFlow("")
    val weight: StateFlow<String> = _weight
    fun onWeightChange(newWeight: String) {
        _weight.value = newWeight
    }

    private val _height = MutableStateFlow("")
    val height: StateFlow<String> = _height

    fun onHeightChange(newHeight: String) {
        _height.value = newHeight
    }

    private val _loseWeight = MutableStateFlow("")
    val loseWeight: StateFlow<String> = _loseWeight

    fun onLoseWeightChange(newLoseWeight: String) {
        _loseWeight.value = newLoseWeight
    }

    fun addUser(user: User){
        users.add(user)
    }







}