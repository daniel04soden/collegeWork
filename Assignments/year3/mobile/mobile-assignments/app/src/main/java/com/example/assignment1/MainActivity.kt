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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment1.Data.AppDatabase
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.ViewModels.EntryViewModelFactory
import com.example.assignment1.ViewModels.UserViewModel
import com.example.assignment1.ViewModels.UserViewModelFactory
import com.example.assignment1.Views.EntryDetailsView
import com.example.assignment1.Views.HomeScreen
import com.example.assignment1.Views.LogScreen
import com.example.assignment1.Views.LoginView
import com.example.assignment1.Views.Screen
import com.example.assignment1.Views.SettingsScreen
import com.example.assignment1.Views.SignUpView
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
    val entryViewModelFactory = EntryViewModelFactory(entryDao)
    val userViewModel: UserViewModel = viewModel(factory = userViewModelFactory)
    val entryViewModel: EntryViewModel = viewModel(factory = entryViewModelFactory)

    Scaffold(
        //TODO bottomBar = { BottomBar(navController) } - add back bottom bar in ergonomic way
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Logs.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Settings.route) { SettingsScreen(userViewModel, navController) }
            composable(Screen.Login.route) { LoginView(viewModel = userViewModel,navController) }
            composable(Screen.SignUp.route) { SignUpView(viewModel = userViewModel,navController) }
            composable(Screen.Logs.route) { LogScreen(entryViewModel) }
            composable(Screen.EntryDetail.route) { backStackEntry ->
                val entryId = backStackEntry.arguments?.getString("entryId")?.toIntOrNull()
                if (entryId != null) {
                    EntryDetailsView(
                        entryId = entryId,
                        entryViewModel = entryViewModel,
                        navController = navController
                    )
                }
            }
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
