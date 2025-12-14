package com.example.assignment1.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.Data.Repository.UserRepository
import com.example.assignment1.Models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun onUserChange(newUser: User?) {
        _currentUser.value = newUser
    }

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    // Signup info
    private val _fullName = MutableStateFlow("")
    val fullName: StateFlow<String> = _fullName

    fun onFullNameChange(newName: String) {
        _fullName.value = newName
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

    private val _caloriesPerDay = MutableStateFlow("")
    val caloriesPerDay: StateFlow<String> = _caloriesPerDay

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

    fun onCaloriesPerDayChange(caloriesPerday: String) {
        _caloriesPerDay.value = caloriesPerday
    }

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError

    private val _usernameError = MutableStateFlow<String?>(null)
    val usernameError: StateFlow<String?> = _usernameError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError

    private val _confirmPasswordError = MutableStateFlow<String?>(null)
    val confirmPasswordError: StateFlow<String?> = _confirmPasswordError

    private val _ageError = MutableStateFlow<String?>(null)
    val ageError: StateFlow<String?> = _ageError

    private val _weightError = MutableStateFlow<String?>(null)
    val weightError: StateFlow<String?> = _weightError

    private val _heightError = MutableStateFlow<String?>(null)
    val heightError: StateFlow<String?> = _heightError

    private val _genderError = MutableStateFlow<String?>(null)
    val genderError: StateFlow<String?> = _genderError

    private val _loseWeightError = MutableStateFlow<String?>(null)
    val loseWeightError: StateFlow<String?> = _loseWeightError

    private fun validateSignUpFields(): Boolean {
        _usernameError.value = null
        _emailError.value = null
        _passwordError.value = null
        _confirmPasswordError.value = null
        _ageError.value = null
        _weightError.value = null
        _heightError.value = null
        _genderError.value = null
        _loseWeightError.value = null

        var isValid = true

        if (fullName.value.isBlank()) {
            _usernameError.value = "Please enter a username"
            isValid = false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            _emailError.value = "Please enter a valid email"
            isValid = false
        }

        if (password.value.length < 8) {
            _passwordError.value = "Password must be at least 8 characters long"
            isValid = false
        }

        if (confirmPassword.value != password.value) {
            _confirmPasswordError.value = "Passwords do not match"
            isValid = false
        }

        if (age.value.toIntOrNull() == null || age.value.toInt() <= 0) {
            _ageError.value = "Please enter a valid age"
            isValid = false
        }

        if (weight.value.toDoubleOrNull() == null || weight.value.toDouble() <= 0) {
            _weightError.value = "Please enter a valid weight (e.g., 75.5)"
            isValid = false
        }

        if (height.value.toDoubleOrNull() == null || height.value.toDouble() <= 0) {
            _heightError.value = "Please enter a valid height (e.g., 177.0)"
            isValid = false
        }

        if (gender.value.isEmpty()) {
            _genderError.value = "Please select a gender"
            isValid = false
        }

        if (loseWeight.value.isEmpty()) {
            _loseWeightError.value = "Please select an option for weight loss"
            isValid = false
        }

        return isValid
    }

    fun validateLogin(): Boolean {
        _emailError.value = null
        _passwordError.value = null

        var isValid = true

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
            _emailError.value = "Please enter a valid email"
            isValid = false
        }

        if (_password.value.isBlank()) {
            _passwordError.value = "Please enter a password"
            isValid = false
        }

        return isValid
    }

    private fun hashPass(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun checkPassword(password: String, hashed: String): Boolean {
        return BCrypt.checkpw(password, hashed)
    }

    suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String,
        username: String,
        age: String,
        gender: String,
        loseWeight: String,
        weight: String,
        height: String
    ): Boolean {
        if (!validateSignUpFields()) {
            return false
        }

        if (userRepository.findUserByEmail(email) != null) {
            _emailError.value = "User with this email already exists"
            return false
        }

        try {
            val hashPassword = hashPass(password)
            val newUser = User(
                fullName = username,
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
                    loseWeight
                )
            )
            val userId = userRepository.insert(newUser)
            val user = userRepository.getUserById(userId)
            _currentUser.value = user
            Log.d("UsersTrack", "New user: $user, now logged in")
            return true
        } catch (e: Exception) {
            Log.e("SignUpError", "Error during sign up", e)
            return false
        }
    }

    fun logOut() {
        _currentUser.value = null
        Log.d("UsersTrack", "User logged out")
    }

    fun loadSettings() {
        _currentUser.value?.let { user ->
            _fullName.value = user.fullName
            _age.value = user.age.toString()
            _email.value = user.email
            _weight.value = user.weight.toString()
            _height.value = user.height.toString()
            _loseWeight.value = user.loseWeight
            _caloriesPerDay.value = user.caloriesPerDay.toString()
        }
    }

    suspend fun updateUser(user: User) {
        userRepository.updateUser(user)
        onUserChange(user)
    }

    suspend fun logIn(email: String, password: String): Boolean {
        if (!validateLogin()) {
            return false
        }

        val user = userRepository.findUserByEmail(email)
        if (user == null) {
            Log.d("UsersTrack", "User not found")
            _emailError.value = "User with this email does not exist."
            return false
        } else {
            if (checkPassword(password, user.password)) {
                Log.d("UsersTrack", "User: $user logged in")
                _currentUser.value = user
                checkForDailyReset(user)
                return true
            } else {
                _passwordError.value = "Incorrect password."
                return false
            }
        }
    }

    fun clearAllErrors() {
        _usernameError.value = null
        _emailError.value = null
        _passwordError.value = null
        _confirmPasswordError.value = null
        _ageError.value = null
        _weightError.value = null
        _heightError.value = null
        _genderError.value = null
        _loseWeightError.value = null
    }

    fun checkForDailyReset(user: User) {
        viewModelScope.launch {
            val today = LocalDate.now()
            val lastJournalUpdate = user.lastJournalEntryDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE) }

            if (lastJournalUpdate == null || lastJournalUpdate.isBefore(today)) {
                userRepository.updateUserJournalStreak(user.id, 0, today.toString())
                // Refresh user data
                _currentUser.value = userRepository.getUserById(user.id)
            }
        }
    }
}
