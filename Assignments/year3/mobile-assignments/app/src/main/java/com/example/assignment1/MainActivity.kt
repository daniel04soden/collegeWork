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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.Views.BottomBar
import com.example.assignment1.Views.EntryDetailsView
import com.example.assignment1.Views.EntryScreen
import com.example.assignment1.Views.LogScreen
import com.example.assignment1.Views.Screen
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
    val entryViewModel: EntryViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { EntryScreen(entryViewModel, navController) }
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
