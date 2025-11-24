package com.example.assignment1.Views

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment1.R
import com.example.assignment1.ViewModels.UserViewModel


@Composable
fun ImprovementImage() {
    Image(painter = painterResource( R.drawable.improve),
        contentDescription = "Scaled Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun LoginView(viewModel: UserViewModel=viewModel(), navController: NavController){
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()


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
                Text(text = "Welcome to Improvio: Login here")
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
                    value = password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text(text = "Enter a new password") },
                    visualTransformation = PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start your journey with us!")
           ImprovementImage()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val loginSuccess = viewModel.logIn(
                        email=email,
                        password=password,
                    )
                    viewModel.onEmailChange("")
                    viewModel.onPasswordChange("")

                    if (loginSuccess){
                        navController.navigate("home")
                        println("Login Successful")
                    }else{
                        navController.navigate("login")
                        println("Login failed")
                    }},
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = 48.dp)
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { navController.navigate("signUp") },
            ) {
                Text(text = "Don't have an account yet?", textAlign = TextAlign.Center)
            }
        }
    }
}