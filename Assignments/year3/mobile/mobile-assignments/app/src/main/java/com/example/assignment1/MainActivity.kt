package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment1.Data.AppDatabase
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.ViewModels.EntryViewModelFactory
import com.example.assignment1.ViewModels.UserViewModel
import com.example.assignment1.ViewModels.UserViewModelFactory
import com.example.assignment1.Views.BottomBar
import com.example.assignment1.Views.HomeScreen
import com.example.assignment1.Views.LogScreen
import com.example.assignment1.Views.LoginView
import com.example.assignment1.Views.Screen
import com.example.assignment1.Views.SignUpView
import com.example.assignment1.Views.UserSettingsScreen
import com.example.assignment1.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                ){
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userDao = AppDatabase.getDatabase(context).userDao()
    val entryDao = AppDatabase.getDatabase(context).entryDao()
    val userViewModelFactory = UserViewModelFactory(userDao)
    val userViewModel: UserViewModel = viewModel(factory = userViewModelFactory)
    val entryViewModelFactory = EntryViewModelFactory(entryDao,userViewModel)
    val entryViewModel: EntryViewModel = viewModel(factory = entryViewModelFactory)
    val screensWithBar = listOf(
        Screen.Home.route,
        Screen.Settings.route,
        Screen.Logs.route
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showingBottomBar = navBackStackEntry?.destination?.route in screensWithBar

    Scaffold(
        bottomBar = {
            if (showingBottomBar){
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Settings.route) { UserSettingsScreen(navController, userViewModel) }
            composable(Screen.Login.route) { LoginView(viewModel = userViewModel,navController) }
            composable(Screen.SignUp.route) { SignUpView(viewModel = userViewModel,navController) }
            composable(Screen.Logs.route) { LogScreen(entryViewModel) }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    AppTheme {
        MainScreen()
    }
}
