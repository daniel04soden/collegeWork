package com.example.assignment1.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,viewModel: UserViewModel){
    Scaffold(
        topBar = { 
            TopAppBar(
                title= {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Improvio: Home")
                    }
                }
            )
                 },
        content = { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(
                    "Journalling",
                    viewModel.currentUser.value?.journalStreak ?: 0 ,
                    onClick = {
                    navController.navigate("journalling")
                })
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(
                    "Meditation",
                    viewModel.currentUser.value?.journalStreak ?: 0 ,
                    onClick = {
                    navController.navigate("meditation")
                })
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(
                    "Food Tracking",
                    viewModel.currentUser.value?.journalStreak ?: 0 ,
                    onClick = {
                    navController.navigate("food")
                })
            }
        }
    )
}