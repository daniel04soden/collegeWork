package com.example.fitnesslogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnesslogger.ui.theme.FitnessLoggerTheme

sealed class Screen(val route: String, val title :String){
    object Register: Screen("register","Register")

    object Login: Screen("login","Login")
    object Track: Screen("home","Home")
    object Settings: Screen("settings","Settings")
    object Profile: Screen("profile","Profile")
}

@Composable
fun BottomNavigationBar(navController:NavHostController){
    val items = listOf(Screen.Track,Screen.Profile,Screen.Settings)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        items.forEach {
                item->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {navController.navigate(item.route)},
                label = {Text(text=item.title)},
                icon = {}
            )
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {BottomNavigationBar(navController=navController)}
    ){innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Track.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Track.route) {
                Track()
            }
            composable(Screen.Profile.route) {
                Profile()
            }
            composable(Screen.Settings.route) {
                Settings()
            }
        }
    }
}


@Composable
fun Track(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

    }
}

@Composable
fun Settings(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Settings page")
    }
}

@Composable
fun RegisterUserDetails(){
    var email by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var confirmPasswd by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray )
            .padding(top= Dp(56f)),
        contentAlignment = Alignment.Center
    ){
        Box(modifier = Modifier.fillMaxSize(0.5f).align(Alignment.TopCenter)){
            Column(Modifier.background(Color.White).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row {
                    Text("Register")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row{
                    Text("Account Details")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    TextField(
                        value = email,
                        onValueChange = {email = it},
                        label = {
                            Text("Enter a new email")
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    TextField(
                        value = passwd,
                        onValueChange = {passwd = it},
                        label = {
                            Text("Enter a safe and secure password")

                        },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    TextField(
                        value = confirmPasswd,
                        onValueChange = {confirmPasswd = it},
                        label = {
                            Text("Re-enter a safe and secure password")

                        },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(){
                    Button(
                        onClick = TODO(),
                        modifier = TODO(),
                        enabled = TODO(),
                        shape = TODO(),
                        colors = TODO(),
                        elevation = TODO(),
                        border = TODO(),
                        contentPadding = TODO(),
                        interactionSource = TODO()
                    ) {Text("Back")}
                    Button(
                        onClick = TODO(),
                        modifier = TODO(),
                        enabled = TODO(),
                        shape = TODO(),
                        colors = TODO(),
                        elevation = TODO(),
                        border = TODO(),
                        contentPadding = TODO(),
                        interactionSource = TODO()
                    ) {Text("Submit")}
                }
            }
        }
    }
}
@Composable
fun RegisterPersonalDetails(){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray )
            .padding(top= Dp(56f)),
        contentAlignment = Alignment.Center
    ){
        Box(modifier = Modifier.fillMaxSize(0.5f).align(Alignment.TopCenter)){
            Column(Modifier.background(Color.White).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row {
                    Text("Register")
                }
                Row{
                    Text("Personal Details")
                }

                Row() {
                    TextField(
                        value = firstName,
                        onValueChange = {firstName = it},
                        label = {
                            Text("First name")

                        },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    TextField(
                        value = lastName,
                        onValueChange = {lastName = it},
                        label = {
                            Text("Last name")

                        },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    TextField(
                        value = dateOfBirth,
                        onValueChange = {dateOfBirth = it},
                        label = {
                            Text("Date of Birth")

                        },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(){
                    Button(
                        onClick = TODO(),
                        modifier = TODO(),
                        enabled = TODO(),
                        shape = TODO(),
                        colors = TODO(),
                        elevation = TODO(),
                        border = TODO(),
                        contentPadding = TODO(),
                        interactionSource = TODO()
                    ) {Text("Next")}
                }
            }
        }

    }
}

@Composable
fun Login(){
    var email by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.LightGray ).padding(top= Dp(56f)),
        contentAlignment = Alignment.Center
    ){
        Box(modifier = Modifier.fillMaxSize(0.5f).align(Alignment.TopCenter)){
            Column(Modifier.background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text("Login Page")
            }
        }

    }
}


@Composable
fun Profile(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Profiles page")
    }
}


@Preview(showBackground = true)
@Composable
fun TrackPreview() {
    FitnessLoggerTheme {
        Track()
    }
}
@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    FitnessLoggerTheme {
        Settings()
    }
}
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    FitnessLoggerTheme {
        Profile()
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    FitnessLoggerTheme {
        RegisterPersonalDetails()
    }
}

@Preview(showBackground = true)

@Composable
fun LoginPreview() {
    FitnessLoggerTheme {
        RegisterPersonalDetails()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessLoggerTheme {
                RegisterPersonalDetails()
            }
        }
    }
}