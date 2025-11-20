package com.example.assignment1.ViewModels
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.assignment1.Models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.mindrot.jbcrypt.BCrypt


class UserViewModel: ViewModel(){
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> = _users


    // Login info
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    // Signup info

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun onUserNameChange(newName: String) {
        _username.value = newName
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
    val confirmPassword: StateFlow<String> = _confirmPassword

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
        _users.value += user
    }

    fun hashPass(password:String,confirmPassword: String): String? {
        if (password != confirmPassword){
            println("Passwords do not match")
            return null
        }
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun checkPassword(password: String, hashed: String): Boolean {
        return BCrypt.checkpw(password, hashed)
    }

    fun signUp(
        email: String,
        password:String,
        confirmPassword: String,
        username: String,
        age: String,
        gender: String,
        loseWeight: String,
        weight: String,
        height: String
    ):Boolean
    {
        val hashPassword = hashPass(password, confirmPassword)
        if (hashPassword.isNullOrEmpty()){
            return false
        }

        val user = User(
            username = username,
            age = age.toInt(),
            gender = gender,
            email = email,
            password = hashPassword,
            weight = weight.toDouble(),
            height = height.toDouble(),
            loseWeight = loseWeight,
            caloriesPerDay = User.calculateCaloriesPerDay(
                weight.toDouble(),
                height.toDouble(),
                age.toInt(),
                gender,
                loseWeight)
        )
        Log.d("UsersTrack", "New user: ${user.toString()}")
        addUser(user)
        Log.d("UsersTrack", "Current users: ${users.toString()}")
        return true
    }

    fun logIn(email: String, password:String, ):Boolean{
        for (user in _users.value){
            if (user.email == email){
                val checkPassword = checkPassword(password, user.password)
                if (checkPassword){
                    Log.d("UsersTrack", "User: ${user.toString()} logged in")
                    return true
                }
            }
        }
        return false
    }
}