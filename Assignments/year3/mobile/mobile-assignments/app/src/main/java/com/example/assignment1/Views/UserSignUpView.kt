package com.example.assignment1.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
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
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.UserViewModel

@Composable
fun SignUpView(viewModel: UserViewModel = viewModel(), navController: NavController) {
	val email by viewModel.email.collectAsState()
	val password by viewModel.password.collectAsState()
	val confirmPassword by viewModel.confPswd.collectAsState()
	val name by viewModel.name.collectAsState()
	val age by viewModel.age.collectAsState()
	val gender by viewModel.gender.collectAsState()
	val loseWeight by viewModel.loseWeight.collectAsState()
	val weight by viewModel.weight.collectAsState()
	val height by viewModel.height.collectAsState()


	//TODO Add back in top bar and take out from bottom bar - only there for testing at the moment

	Box(
		modifier = Modifier
			.padding(Dp(20F))
			.fillMaxHeight(1F)
			.fillMaxWidth(1F)
	) {
		Column(
			modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(text = "Welcome to Improvio: Sign Up here")
			OutlinedTextField(
				value = email,
				onValueChange = { viewModel.onEmailChange(it) },
				label = { Text(text = "Email") },
				placeholder = { Text("YourEmail@example.com") },
			)
			Spacer(modifier = Modifier.fillMaxHeight(0.05F))
			OutlinedTextField(
				value = name,
				onValueChange = { viewModel.onNameChange(it) },
				label = { Text(text = "Name") },
				placeholder = { Text("Enter Your Full Name") },
			)
			Spacer(modifier = Modifier.fillMaxHeight(0.05F))
			OutlinedTextField(
				value = password,
				onValueChange = { viewModel.onPasswordChange(it) },
				label = { Text(text = "Enter a new password") },
				visualTransformation = PasswordVisualTransformation(),
			)

			Spacer(modifier = Modifier.fillMaxHeight(0.05F))
			OutlinedTextField(
				value = confirmPassword,
				onValueChange = { viewModel.onConfirmPasswordChange(it) },
				label = { Text(text = "Re-enter your new password to confirm") },
				visualTransformation = PasswordVisualTransformation(),
			)
			Spacer(modifier = Modifier.fillMaxHeight(0.05F))

            OutlinedTextField(
                value = age,
                onValueChange = { viewModel.onAgeChange(it) },
                label = { Text(text = "Age") },
                placeholder = { Text("99") },
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05F))

            OutlinedTextField(
                value = gender,
                onValueChange = { viewModel.onGenderChange(it) },
                label = { Text(text = "Gender") },
                placeholder = { Text("(M/F)") },
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05F))

            OutlinedTextField(
                value = loseWeight,
                onValueChange = { viewModel.onLoseWeightChange(it) },
                label = { Text(text = "Weight Loss") },
                placeholder = { Text("(Y/N)") },
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05F))

            OutlinedTextField(
                value = weight,
                onValueChange = { viewModel.onWeightChange(it) },
                label = { Text(text = "Weight (kg)") },
                placeholder = { Text("170") },
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05F))

            OutlinedTextField(
                value = height,
                onValueChange = { viewModel.onHeightChange(it) },
                label = { Text(text = "Height (cm)") },
                placeholder = { Text("177") },
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05F))

			Column(Modifier.align(Alignment.CenterHorizontally)) {
				Row(Modifier.weight(1F)) {
					Button(
						//TODO onClick = { navController.navigate("home") }, - leave out for now
						onClick = { print("Test") },
						modifier = Modifier
							.fillMaxWidth(0.9F)
							.fillMaxHeight(0.45F)
							.requiredHeightIn(min = Dp(45F))
					) {
						Text(text = "Sign Up")
					}
				}

				Spacer(modifier = Modifier.fillMaxHeight(0.05F))
				Text("Already have an account?")
				Row(Modifier.weight(1F)) {
					Button(
						onClick = { navController.navigate("login") },
					) {
						Text(text = "Sign up", textAlign = TextAlign.Center)
					}
				}
			}
		}
	}
}
