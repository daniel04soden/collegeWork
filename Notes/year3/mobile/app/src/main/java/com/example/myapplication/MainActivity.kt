package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextExample()
        }
    }
}

@Composable
fun TestImage(){
Box(Modifier.fillMaxSize()) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Android loop",
        modifier = Modifier.align(Alignment.Center),
        contentScale = ContentScale.Crop // or ContentScale.Fit or other enum, 2.5 is invalid
    )
}
}

@Composable
fun TextExample() {
    var msg by rememberSaveable { mutableStateOf(" ") }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = msg,
            onValueChange = { msg = it },
            placeholder = { Text("Enter text") }
        )
        Spacer(Modifier.height(16.dp))
        Text(text = msg)
        Text(text = "Number of vowels: ${countVowels(msg)}")
        TestImage()
    }
}

@Composable
fun TestlazyColumn(){
    val itemsList = listOf(
        "1","2","3","4","5","6"
    )
    var selectedItem by remember { mutableStateOf<String?>(null) }

    Column(Modifer.fillMaxSize()){
        items(itemsList){
            item->
            Row (
                verticalAlignment = Alignment.Center,
                Modifier.fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp)
                    .background(Color.Cyan),
                verticalAlignment = Alignment.CenterVertically
            )

            {
                Text(text=item,modifier= Modifier.padding(16 .dp)
                    .clickable(onClick = {selectedItem=item})
                )

            }
        }
        if (selectedItem !=null){
            Text("Selected item $selectedItem")
        }
    }
}

fun countVowels(msg:String) :Int{

    val vowels = listOf("a","e","i","o","u")

    var count = 0
    for (char in msg){
       if (char.lowercase() in vowels) {
            count++
       }
    }
    return count
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
       TextExample()
    }
}