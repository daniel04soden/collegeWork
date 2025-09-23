package com.example.labwk3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwk3.ui.theme.Labwk3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labwk3Theme {
                LazyWonderOfWorld()
            }
        }
    }
}

@Composable
fun WonderOfWorld() {
    val names = listOf("Pyramid", "Taj Mahal", "Big Ben")
    val descriptions = listOf(
        "Egyptian pyramids are ancient monumental structures, primarily built as tombs for pharaohs and their consorts during the Old and Middle Kingdoms, with the Great Pyramid of Giza being the largest and most famous",
        "The Taj Mahal (/ˌtɑːdʒ məˈhɑːl, ˌtɑːʒ -/ TAHJ mə-HAHL, TAHZH -\u2060; Hindustani: [taːdʒ ˈmɛɦ(ɛ)l]; lit. 'Crown of the Palace') is an ivory-white marble mausoleum on the right bank of the river Yamuna in Agra, Uttar Pradesh, India. It was commissioned in 1631 by the fifth Mughal emperor, Shah Jahan (r. 1628–1658), to house the tomb of his beloved wife, Mumtaz Mahal; it also houses the tomb of Shah Jahan himself. The tomb is the centrepiece of a 17-hectare (42-acre) complex, which includes a mosque and a guest house, and is set in formal gardens bounded on three sides by a crenellated wall",
        "Big Ben is the nickname for the Great Bell of the Great Clock of Westminster,[1][2] and, by extension, for the clock tower itself,[3] which stands at the north end of the Palace of Westminster in London, England.[4] Originally named the Clock Tower, it was renamed Elizabeth Tower in 2012 to mark the Diamond Jubilee of Queen Elizabeth II. The clock is a striking clock with five bells.[2]"
    )

    var i by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                when (i) {
                    0 -> PyramidImage()
                    1 -> TajMahalImage()
                    2 -> BigBenImage()
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = names[i])
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Text(text=descriptions[i], Modifier.background(Color.Blue))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        i = if (i == 0) names.size - 1 else i - 1
                    }
                ) {
                    Text("Previous")
                }
                Button(
                    onClick = {
                        i = (i + 1) % names.size
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun LazyWonderOfWorld() {
    var information by remember { mutableStateOf("") }
    val names = listOf("Pyramid", "Taj Mahal", "Big Ben","Background","Android uhh")
    val descriptions = listOf(
        "Egyptian pyramids are ancient monumental structures, primarily built as tombs for pharaohs and their consorts during the Old and Middle Kingdoms, with the Great Pyramid of Giza being the largest and most famous",
        "The Taj Mahal (/ˌtɑːdʒ məˈhɑːl, ˌtɑːʒ -\u2060; Hindustani: [taːdʒ ˈmɛɦ(ɛ)l]; lit. 'Crown of the Palace') is an ivory-white marble mausoleum on the right bank of the river Yamuna in Agra, Uttar Pradesh, India. It was commissioned in 1631 by the fifth Mughal emperor, Shah Jahan (r. 1628–1658), to house the tomb of his beloved wife, Mumtaz Mahal; it also houses the tomb of Shah Jahan himself. The tomb is the centrepiece of a 17-hectare (42-acre) complex, which includes a mosque and a guest house, and is set in formal gardens bounded on three sides by a crenellated wall",
        "Big Ben is the nickname for the Great Bell of the Great Clock of Westminster,[1][2] and, by extension, for the clock tower itself,[3] which stands at the north end of the Palace of Westminster in London, England.[4] Originally named the Clock Tower, it was renamed Elizabeth Tower in 2012 to mark the Diamond Jubilee of Queen Elizabeth II. The clock is a striking clock with five bells.[2]"
        ,"What are you doing this is wrong",
        "W android"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.tajmahal),
                        contentDescription = "Taj mahal image",
                        modifier = Modifier.size(100.dp).clickable {
                            val title = names[0]
                            val desc = descriptions[0]
                            information = "$title\n$desc"
                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.big_ben),
                        contentDescription = "Big ben Image",
                        modifier = Modifier.size(100.dp).clickable {
                            val title = names[1]
                            val desc = descriptions[1]
                            information = "$title\n$desc"
                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.pyramid),
                        contentDescription = "Pyramid of egypt",
                        modifier = Modifier.size(100.dp).clickable {
                            val title = names[2]
                            val desc = descriptions[2]
                            information = "$title\n$desc"
                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "background image",
                        modifier = Modifier.size(100.dp).clickable {
                            val title = names[3]
                            val desc = descriptions[3]
                            information = "$title\n$desc"
                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "android foreground image",
                        modifier = Modifier.size(100.dp).clickable {
                            val title = names[4]
                            val desc = descriptions[4]
                            information = "$title\n$desc"
                        }
                    )

                }
            }
            LazyColumn(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            ){

                item {
                    Text(information)
                }
            }

        }
    }
}



@Composable
fun PyramidImage() {
    Image(painter = painterResource(id = R.drawable.pyramid),
        contentDescription = "Pyramid",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Set size for the Image
        contentScale = ContentScale.FillBounds // Scale option
    )
}

@Composable
fun TajMahalImage() {
    Image(painter = painterResource(id = R.drawable.tajmahal),
        contentDescription = "Taj mahal",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Set size for the Image
        contentScale = ContentScale.FillBounds // Scale option
    )
}

@Composable
fun BigBenImage() {
    Image(painter = painterResource(id = R.drawable.big_ben),
        contentDescription = "Big Ben",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Set size for the Image
        contentScale = ContentScale.FillBounds // Scale option
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Labwk3Theme {
        LazyWonderOfWorld()
    }
}