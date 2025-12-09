package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment1.Data.AppDatabase
import com.example.assignment1.SessionManager
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.ViewModels.EntryViewModelFactory
import com.example.assignment1.ViewModels.FoodViewModel
import com.example.assignment1.ViewModels.MeditationViewModel
import com.example.assignment1.ViewModels.UserViewModel
import com.example.assignment1.ViewModels.UserViewModelFactory
import com.example.assignment1.Views.BottomBar
import com.example.assignment1.Views.FoodScreen
import com.example.assignment1.Views.HomeScreen
import com.example.assignment1.Views.LogScreen
import com.example.assignment1.Views.LoginView
import com.example.assignment1.Views.MeditationScreen
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
    // Assuming SessionManager is in the Data package now
    val sessionManager = SessionManager(context)
    val userViewModelFactory = UserViewModelFactory(userDao, sessionManager)
    val userViewModel: UserViewModel = viewModel(factory = userViewModelFactory)
    val entryViewModelFactory = EntryViewModelFactory(entryDao, userViewModel)
    val entryViewModel: EntryViewModel = viewModel(factory = entryViewModelFactory)
    val foodViewModel: FoodViewModel = viewModel()
    val meditationViewModel: MeditationViewModel = viewModel()

    val currentUser by userViewModel.currentUser.collectAsState()

    val isUserDoneLoading = userViewModel.isAutoLoginCheckComplete.collectAsState().value

    val screensWithBar = listOf(
        Screen.Home.route,
        Screen.Settings.route,
        Screen.Journalling.route,
        Screen.Food.route,
        Screen.Meditation.route
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showingBottomBar = navBackStackEntry?.destination?.route in screensWithBar

    val startDestination = "landing"

    LaunchedEffect(key1 = isUserDoneLoading) {
        if (isUserDoneLoading) {
            val destination = if (currentUser != null) Screen.Home.route else Screen.Login.route
            navController.navigate(destination) {
                popUpTo(startDestination) { inclusive = true }
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (showingBottomBar){
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("landing") {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            composable(Screen.Home.route) { HomeScreen(navController, userViewModel) }
            composable(Screen.Settings.route) { UserSettingsScreen(navController, userViewModel) }
            val onAuthSuccess: () -> Unit = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }

            composable(Screen.Login.route) {
                LoginView(
                    viewModel = userViewModel,
                    navController = navController,
                    onLoginSuccess = onAuthSuccess
                )
            }
            composable(Screen.SignUp.route) {
                SignUpView(
                    viewModel = userViewModel,
                    navController = navController,
                    onSignUpSuccess = onAuthSuccess
                )
            }

            composable(Screen.Journalling.route) { LogScreen(entryViewModel) }
            composable ( Screen.Food.route ) { FoodScreen(foodViewModel) }
            composable ( Screen.Meditation.route ) { MeditationScreen(meditationViewModel) }
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
