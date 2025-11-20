package com.example.assignment1.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.UserViewModel

@Composable
fun SignUpView(viewModel: UserViewModel = viewModel(), navController: NavController) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val username by viewModel.username.collectAsState()
    val age by viewModel.age.collectAsState()
    val gender by viewModel.gender.collectAsState()
    val loseWeight by viewModel.loseWeight.collectAsState()
    val weight by viewModel.weight.collectAsState()
    val height by viewModel.height.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp), // Use .dp for padding
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
                OutlinedTextField(
                    value = email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text(text = "Email") },
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { viewModel.onUserNameChange(it) },
                    label = { Text(text = "Name") },
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text(text = "Enter a new password") },
                    visualTransformation = PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordChange(it) },
                    label = { Text(text = "Confirm password") },
                    visualTransformation = PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = age,
                    onValueChange = { viewModel.onAgeChange(it) },
                    label = { Text(text = "Age") },
                    placeholder = { Text("99") },
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = gender,
                    onValueChange = { viewModel.onGenderChange(it) },
                    label = { Text(text = "Gender") },
                    placeholder = { Text("(M/F)") },
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = loseWeight,
                    onValueChange = { viewModel.onLoseWeightChange(it) },
                    label = { Text(text = "Weight Loss") },
                    placeholder = { Text("(Y/N)") },
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = weight,
                    onValueChange = { viewModel.onWeightChange(it) },
                    label = { Text(text = "Weight (kg)") },
                    placeholder = { Text("170") },
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = height,
                    onValueChange = { viewModel.onHeightChange(it) },
                    label = { Text(text = "Height (cm)") },
                    placeholder = { Text("177") },
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // Space above the button

            Button(
                onClick = {
                   val signUpSuccess = viewModel.signUp(
                       email=email,
                       username=username,
                       password=password,
                       confirmPassword=confirmPassword,
                       age=age,
                       gender=gender,
                       loseWeight=loseWeight,
                       weight=weight,
                       height=height
                   )

                    viewModel.onEmailChange("")
                    viewModel.onPasswordChange("")
                    viewModel.onConfirmPasswordChange("")
                    viewModel.onUserNameChange("")
                    viewModel.onAgeChange("")
                    viewModel.onGenderChange("")
                    viewModel.onLoseWeightChange("")
                    viewModel.onWeightChange("")
                    viewModel.onHeightChange("")

                    if (signUpSuccess){
                        navController.navigate("login")
                    }else{
                        println("Sign up fail")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = 48.dp)
            ) {
                Text(text = "Sign Up")
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { navController.navigate("login") },
            ) {
                Text(text = "Already have an account?", textAlign = TextAlign.Center)
            }
        }
    }

}
