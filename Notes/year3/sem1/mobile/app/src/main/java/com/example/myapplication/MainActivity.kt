package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination="screen1"){
                composable("screen1"){Screen1(navController)}
                composable("screen2/{msg}"){
                    backStackEntry ->
                    val msg = backStackEntry.arguments?.getString("msg") ?:""
                    Screen2(navController,msg)
                }
                composable("screen3/{msg}"){
                        backStackEntry ->
                    val msg = backStackEntry.arguments?.getString("msg") ?:""
                    Screen3(navController,msg)
                }
            }
        }
    }
}
@Composable
fun Screen1(navController: NavController){
    var textMsg by rememberSaveable { mutableStateOf("") }

    Column (
        Modifier.fillMaxSize().background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        TextField(
            value = textMsg,
            onValueChange = {textMsg = it},
            label = {Text("enter a message")}
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("screen2/${textMsg}")
        }){
           Text("Move to screen 2")
        }
    }
}


@Composable
fun Screen2(navController: NavController,textMsg:String?){
    Column (
        Modifier.fillMaxSize().background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text("mgs is $textMsg")

        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("screen1")
        }){
            Text("Move to screen 1")
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("screen3/${textMsg}")
        }){
            Text("Move to screen 3")
        }
    }
}
@Composable
fun Screen3(navController: NavController,textMsg: String?){
    Column (
        Modifier.fillMaxSize().background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text("You made an epic 3rd screen")
        Text("We are keeping the epic message of $textMsg")

        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("screen2/${textMsg}")
        }){
            Text("Move to screen 2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen1Preview() {
    Screen1(navController = rememberNavController())
}
@Preview(showBackground = true)
@Composable
fun Screen2Preview() {
    Screen1(navController = rememberNavController())
}
