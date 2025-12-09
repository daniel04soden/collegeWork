package com.example.assignment1.Views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Settings:Screen("settings","Settings",Icons.Default.Settings)
    object Login:Screen("login","Login",Icons.Default.AccountBox)
    object SignUp:Screen("signUp","Sign Up",Icons.Default.AccountCircle)
    object Journalling : Screen("journalling", "Journalling", Icons.Default.DateRange)
    object Food : Screen("food", "Food", Icons.Default.DateRange)
    object Meditation : Screen("meditation", "Meditation", Icons.Default.DateRange)
}


val bottomBarItems = listOf(
    Screen.Home,
    Screen.Settings
)

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomBarItems.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                    }
                }
            )
        }
    }
}