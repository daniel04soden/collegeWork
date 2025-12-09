package com.example.assignment1.Views

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment1.ViewModels.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(viewModel: FoodViewModel = viewModel()) {
    var optionsExpanded by remember { mutableStateOf(false) }
    var context = LocalContext.current
    var calorieFilter by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Food") },
                actions = {
                    Text("Filter by Calorie Count")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box {
                            IconButton(onClick = { optionsExpanded = true }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                            }
                            OptionsDropDownMenu(
                                expanded = optionsExpanded,
                                onDismiss = { optionsExpanded = false },
                                onOptionSelected = { option ->
                                    Toast.makeText(
                                        context,
                                        "$option Clicked", Toast.LENGTH_SHORT
                                    ).show()
                                    calorieFilter = option
                                },
                                options = List(10) { (it + 1).toString() }
                            )
                        }
                        Button(onClick = {
                            calorieFilter = ""
                        }) { Text("Reset filter") }
                    }
                }
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