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
fun LoginView(viewModel: UserViewModel=viewModel(), navController: NavController){
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
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
                Text(text = "Welcome to Improvio: Login")
                OutlinedTextField(
                    value = email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text(text = "Email") },
                    placeholder = { Text("YourEmail@example.com") },
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.05F))
                Column(Modifier.align(Alignment.CenterHorizontally)) {
                    Row(Modifier.weight(1F)) {
                        Button(
                            //TODO onClick = { navController.navigate("home") }, - leave out for now
                            onClick = {print("Test")},
                            modifier = Modifier
                                .fillMaxWidth(0.9F)
                                .fillMaxHeight(0.45F)
                                .requiredHeightIn(min = Dp(45F))
                        ) {
                            Text(text = "Login")
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.05F))
                    Text("Don't have an account?")
                    Row(Modifier.weight(1F)) {
                        Button(
                            onClick = { navController.navigate("signUp") },
                        ) {
                            Text(text = "Sign up", textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
}