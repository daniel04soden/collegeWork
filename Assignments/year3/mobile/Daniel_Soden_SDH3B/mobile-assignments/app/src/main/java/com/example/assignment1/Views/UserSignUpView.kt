package com.example.assignment1.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun SignUpView(
    viewModel: UserViewModel,
    navController: NavController,
    onSignUpSuccess: () -> Unit
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val fullName by viewModel.fullName.collectAsState()
    val age by viewModel.age.collectAsState()
    val gender by viewModel.gender.collectAsState()
    val loseWeight by viewModel.loseWeight.collectAsState()
    val weight by viewModel.weight.collectAsState()
    val height by viewModel.height.collectAsState()

    val usernameError by viewModel.usernameError.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val confirmPasswordError by viewModel.confirmPasswordError.collectAsState()
    val ageError by viewModel.ageError.collectAsState()
    val weightError by viewModel.weightError.collectAsState()
    val heightError by viewModel.heightError.collectAsState()
    val genderError by viewModel.genderError.collectAsState()
    val loseWeightError by viewModel.loseWeightError.collectAsState()

    val genderOptions = listOf("Male", "Female")
    val loseWeightOptions = listOf("Y", "N")
    val scope = rememberCoroutineScope()

    fun clearSignUp(viewModel: UserViewModel){
        viewModel.onEmailChange("")
        viewModel.onPasswordChange("")
        viewModel.onConfirmPasswordChange("")
        viewModel.onFullNameChange("")
        viewModel.onAgeChange("")
        viewModel.onGenderChange("")
        viewModel.onLoseWeightChange("")
        viewModel.onWeightChange("")
        viewModel.onHeightChange("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(text = "Welcome to Improvio: Sign Up here")
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                // Full Name
                OutlinedTextField(
                    value = fullName,
                    onValueChange = viewModel::onFullNameChange,
                    label = { Text(text = "Name") },
                    isError = usernameError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                usernameError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = viewModel::onEmailChange,
                    label = { Text(text = "Email") },
                    isError = emailError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                emailError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = viewModel::onPasswordChange,
                    label = { Text(text = "Enter a new password") },
                    isError = passwordError != null,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                passwordError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = viewModel::onConfirmPasswordChange,
                    label = { Text(text = "Confirm password") },
                    isError = confirmPasswordError != null,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                confirmPasswordError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                // Age
                OutlinedTextField(
                    value = age,
                    onValueChange = viewModel::onAgeChange,
                    label = { Text(text = "Age") },
                    isError = ageError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                ageError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                ReusableDropdown(
                    label = "Gender",
                    options = genderOptions,
                    selectedValue = gender,
                    onValueChange = viewModel::onGenderChange,
                    isError = genderError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                genderError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                // Weight
                OutlinedTextField(
                    value = weight,
                    onValueChange = viewModel::onWeightChange,
                    label = { Text(text = "Weight (kg)") },
                    isError = weightError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                weightError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                // Height
                OutlinedTextField(
                    value = height,
                    onValueChange = viewModel::onHeightChange,
                    label = { Text(text = "Height (cm)") },
                    isError = heightError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                heightError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                Spacer(modifier = Modifier.height(16.dp))

                // Weight Loss Goal Dropdown
                ReusableDropdown(
                    label = "Weight Loss Goal",
                    options = loseWeightOptions,
                    selectedValue = loseWeight,
                    onValueChange = viewModel::onLoseWeightChange,
                    isError = loseWeightError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                loseWeightError?.let { Text(text = it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        val signUpSuccess = viewModel.signUp(
                            email=email,
                            username=fullName,
                            password=password,
                            confirmPassword=confirmPassword,
                            age=age,
                            gender=gender,
                            loseWeight=loseWeight,
                            weight=weight,
                            height=height
                        )
                        if (signUpSuccess) {
                            clearSignUp(viewModel)
                            onSignUpSuccess()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = 48.dp)
            ) {
                Text(text = "Sign Up")
            }

            Button(
                onClick = {
                    clearSignUp(viewModel)
                    viewModel.clearAllErrors()
                    navController.navigate("login")
                },
            ) {
                Text(text = "Already have an account?", textAlign = TextAlign.Center)
            }
        }
    }
}