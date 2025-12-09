package com.example.assignment1.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.assignment1.ViewModels.MeditationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeditationScreen(meditationViewModel: MeditationViewModel) {
    var optionsExpanded by remember { mutableStateOf(false) }
    var context = LocalContext.current
    var dateFilter by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meditation") },
            )
        },
        content = {
                paddingValues ->
            Column(Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ) {

            }
        }
    )


}
