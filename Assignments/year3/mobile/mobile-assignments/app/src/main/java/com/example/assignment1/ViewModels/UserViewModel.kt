package com.example.assignment1.ViewModels
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.Data.UserDao
import com.example.assignment1.Models.User
import com.example.assignment1.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class UserViewModel(val userDao: UserDao,private val sessionManager: SessionManager): ViewModel(){
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _isAutoLoginCheckComplete = MutableStateFlow(false)
    val isAutoLoginCheckComplete: StateFlow<Boolean> = _isAutoLoginCheckComplete
    fun onUserChange(newUser: User?) {
        _currentUser.value = newUser
    }
    init {
        checkForAutoLogin()
    }
    private fun checkForAutoLogin(){
        viewModelScope.launch {
            try {
                val userId = sessionManager.getLoggedInUserId()
                if (userId != -1L) {
                    val user = userDao.findUserById(userId)
                    if (user != null) {
                        _currentUser.value = user
                        checkForDailyReset(user)
                        Log.d("UsersTrack", "Auto-logged in user: ${user.fullName}")
                    } else {
                        sessionManager.clearSession()
                    }
                }
            } finally {
                _isAutoLoginCheckComplete.value = true
            }
        }
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

    // UPDATE THIS LINE to convert to String
    private val _caloriesPerDay = MutableStateFlow("") // Should be a String for the UI
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

    fun onCaloriesPerDayChange(caloriesPerday:String) {
        _caloriesPerDay.value = caloriesPerday
    }

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError

    private val _usernameError = MutableStateFlow<String?>(null)
    val usernameError: StateFlow<String?> = _usernameError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError

    private val _confirmPasswordError = MutableStateFlow<String?>(null)
    val confirmPasswordError: StateFlow<String?> = _passwordError

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


    fun String.hasOnlyNonEmptyLetters(): Boolean {
        return this.isNotEmpty() && this.all { it.isLetter() }
    }

    fun validateLogin():Boolean{
        _emailError.value = null
        _passwordError.value = null

        var isValid = true

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()){
            _emailError.value = "Please enter a valid email"
            isValid = false
        }

        if (_password.value.isBlank()){
            _passwordError.value = "Please enter a password"
            isValid = false
        }

        return isValid
    }

    fun validateSignUp():Boolean{
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

       if (!(_fullName.value.hasOnlyNonEmptyLetters())){
           _usernameError.value = "Please enter a username"
           isValid = false
       }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()){
            _emailError.value = "Please enter a valid email"
            isValid = false
        }

        if (_password.value.length < 8){
            _passwordError.value = "Password must be at least 8 characters long"
            isValid = false
        }

        if (_confirmPassword.value != _password.value){
            _confirmPasswordError.value = "Passwords do not match"
            isValid = false
        }

        if (!_age.value.isDigitsOnly() || _age.value.length > 3 || _age.value.isEmpty()){
            _ageError.value = "Please enter a valid age"
            isValid = false
        }

        if (!_weight.value.isDigitsOnly() || _weight.value.length > 3 || _weight.value.isEmpty()){
            _weightError.value = "Please enter a valid weight (E.g. 75, 120 etc)"
            isValid = false
        }

        if (!_height.value.isDigitsOnly() || _height.value.length > 3 || _weight.value.isEmpty()){
            _heightError.value = "Please enter a valid height (E.g. 177, 130 etc)"
            isValid = false
        }

        if (_gender.value.isEmpty()){
            _genderError.value = "Please select a gender"
            isValid = false
        }

        if (_loseWeight.value.isEmpty()){
            _loseWeightError.value = "Please an option (Y/N Lose weight)"
            isValid = false
        }

        return isValid
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

    suspend fun signUp(
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
        if (validateSignUp()){
            val user = User(
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
            viewModelScope.launch {
                userDao.insertUser(user = user)
            }
            Log.d("UsersTrack", "New user: $user")
            return true
        }
        return false
    }

    fun logOut(){
       _currentUser.value = null
        sessionManager.clearSession()
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
        onUserChange(user)
        userDao.updateUser(user)
    }
    suspend fun logIn(email: String, password: String): Boolean {
        if (!validateLogin()) {
            return false
        }

        val user = userDao.findUserByEmail(email)
        if (user == null) {
            Log.d("UsersTrack", "User not found")
            _emailError.value = "User with this email does not exist."
            return false
        } else {
            if (checkPassword(password, user.password)) {
                Log.d("UsersTrack", "User: $user logged in")
                _currentUser.value = user

                sessionManager.saveUserSession(user.id)

                checkForDailyReset(user)

                return true
            }
            _passwordError.value = "Incorrect password."
            return false
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
            val lastUpdateDate = user.lastCalorieEntryDate?.let {
                LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
            }

            if (lastUpdateDate == null || lastUpdateDate.isBefore(today)) {
                val updatedUser = user.copy(
                    caloriesToday = 0.0,
                    lastCalorieEntryDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE)
                )
                userDao.updateUser(updatedUser)
                _currentUser.value = updatedUser
            }
        }
    }
}