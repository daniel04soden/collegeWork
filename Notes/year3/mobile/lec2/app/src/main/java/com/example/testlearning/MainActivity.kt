package com.example.testlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.testlearning.ui.theme.TestlearningTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorfulBox()
        }
    }
}


@Composable
fun ColorfulBox() {
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the entire available space
    ) {
        Column(
            modifier = Modifier.fillMaxSize() // Fill the available space vertically
        ) {
            val colorList = listOf(
                Color.Red,
                Color.Blue,
                Color.Green,
                Color.Yellow,
                Color.Cyan,
                Color.Magenta,
                Color.LightGray
            )
            var fav:Color = Color.Red

            Row(
                modifier = Modifier
                    .weight(1f)          // Each row gets an equal height
                    .background(Color.Black), // Background color for this row
                horizontalArrangement = Arrangement.Center,      // Center the text horizontally
                verticalAlignment = Alignment.CenterVertically   // Center the text vertically
            ) {
                Text(
                    text = "Favourite Color $fav",
                    color = Color.White
                )
                var clicked by remember { mutableStateOf(false) }
                IconButton(onClick = {
                    clicked = !clicked
                    fav = Color.Blue //TODO cycle colours here
                }) {
                    Icon(
                        imageVector = if (clicked) Icons.Default.Favorite

                        else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (clicked) Color.Red else Color.Gray
                    )
                }

            }
            Column(
                modifier = Modifier
                    .weight(1f)          // Each row gets an equal height
                    .background(Color.Black),) // Background color for this row
            {
                Row {
                    Button(
                        onClick = {println("Hello")},
                    ){
                        Text("Next")
                    }

                }

                Row{
                    Button(
                        onClick = {println("Bye")},
                    ){
                        Text("Previous")
                    }

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ColorfulBoxPreview() {
    TestlearningTheme {
        ColorfulBox()
    }
}