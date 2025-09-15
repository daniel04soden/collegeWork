package com.example.testlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testlearning.ui.theme.TestlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestlearningTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var myList = mutableListOf(Color.Red, Color.Blue,Color.Magenta)
    var myNum = 20
    for (nums in 0..200){
        myNum++
    }

    var count by rememberSaveable { mutableIntStateOf(0)  }

    Column(Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "You have clicked $count too many times")
        Button(onClick={count++}){
            Text(text="Click me pls omg")
        }
    }


    /*
    Box(
        Modifier
        .fillMaxSize()
        .height(100.dp)
        .background(Color.Blue)

    ){
        Text(
            text = "Hello $name! and $myNum",
            color = Color.White,
            modifier = modifier
        )
    }

     */
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestlearningTheme {
        Greeting("Android")
    }
}