package com.example.assignment1.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: UserViewModel = viewModel(),navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
                paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                UserDetailsView(userViewModel = viewModel, navController = navController)
            }
        }

    )
}

@Composable
fun UserDetailsView(userViewModel: UserViewModel, navController: NavController){
    var username by remember {mutableStateOf(userViewModel.currentUser.value?.username)}
    var age by remember {mutableStateOf(userViewModel.currentUser.value?.age)}
    var gender by remember {mutableStateOf(userViewModel.currentUser.value?.gender)}
    var height by remember {mutableStateOf(userViewModel.currentUser.value?.height)}
    var weight by remember {mutableStateOf(userViewModel.currentUser.value?.weight)}
    var loseWeight by remember {mutableStateOf(userViewModel.currentUser.value?.loseWeight)}
    var caloriesPerDay by remember {mutableStateOf(userViewModel.currentUser.value?.caloriesPerDay)}


}