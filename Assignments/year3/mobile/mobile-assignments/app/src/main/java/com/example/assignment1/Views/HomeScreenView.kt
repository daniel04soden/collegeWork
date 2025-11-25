package com.example.assignment1.Views

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun NavButton(navController: NavController, itemName: String,onClick: () -> Unit) {
    Card(
       modifier = Modifier
           .fillMaxWidth()
           .padding(8.dp)
           .clickable{onClick()},
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = itemName,
                    fontWeight = FontWeight.Bold, fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Streak: ${3}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = { 
            TopAppBar(
                title= {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Improvio: Home")
                    }
                }
            )
                 },
        bottomBar = {
            BottomBar(navController)
        },
        content = { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(navController, "Journalling", onClick = {
                    navController.navigate("logs")
                })
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(navController, "Meditation", onClick = {
                    navController.navigate("logs")
                })
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(navController, "Food Tracking", onClick = {
                    navController.navigate("logs")
                })
            }
        }
    )
}