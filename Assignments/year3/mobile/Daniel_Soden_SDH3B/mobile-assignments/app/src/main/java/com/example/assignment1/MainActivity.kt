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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment1.Data.AppDatabase
import com.example.assignment1.Data.Repository.EntryRepository
import com.example.assignment1.Data.Repository.FoodRepository
import com.example.assignment1.Data.Repository.MeditationRepository
import com.example.assignment1.Data.Repository.UserRepository
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.ViewModels.EntryViewModelFactory
import com.example.assignment1.ViewModels.FoodViewModel
import com.example.assignment1.ViewModels.FoodViewModelFactory
import com.example.assignment1.ViewModels.MeditationViewModel
import com.example.assignment1.ViewModels.MeditationViewModelFactory
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
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
    val db = AppDatabase.getDatabase(context)
    val userRepo = UserRepository(db.userDao())
    val entryRepo = EntryRepository(db.entryDao())
    val foodRepo = FoodRepository(db.foodDao())
    val medRepo = MeditationRepository(db.meditationDao())

    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(userRepo))
    val currentUser by userViewModel.currentUser.collectAsState()

    val screensWithBar = listOf(
        Screen.Home.route,
        Screen.Settings.route,
        Screen.Journalling.route,
        Screen.Food.route,
        Screen.Meditation.route
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showingBottomBar = navBackStackEntry?.destination?.route in screensWithBar

    Scaffold(
        bottomBar = {
            if (showingBottomBar) {
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
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

            composable(Screen.Journalling.route) {
                currentUser?.let { user ->
                    val entryViewModel: EntryViewModel = viewModel(
                        factory = EntryViewModelFactory(entryRepo, userRepo, user.id)
                    )
                    LogScreen(entryViewModel, user)
                }
            }
            composable(Screen.Food.route) {
                currentUser?.let { _ ->
                    val foodViewModel: FoodViewModel = viewModel(factory = FoodViewModelFactory(foodRepo, userRepo, userViewModel))
                    FoodScreen(foodViewModel, userViewModel)
                }
            }
            composable(Screen.Meditation.route) {
                currentUser?.let { _ ->
                    val meditationViewModel: MeditationViewModel = viewModel(factory = MeditationViewModelFactory(userViewModel,medRepo))
                    MeditationScreen(navController,meditationViewModel)
                }
            }
        }
    }
}
