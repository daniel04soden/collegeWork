package com.example.assignment1.Views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.EntryDetailsViewModel
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.models.Entry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryDetailsView(entryId: Int, entryViewModel: EntryViewModel, navController: NavController){
    val selectedEntry = entryViewModel.entries.find { it.id == entryId }
    if (selectedEntry != null) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Edit Journal Entry") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            },
            content = {
                    paddingValues ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(paddingValues)
                ) {
                    ViewEditEntry(selectedEntry = selectedEntry, entryViewModel = entryViewModel, navController = navController)
                }
            }

        )
    }
}

@Composable
fun ViewEditEntry(selectedEntry: Entry, entryViewModel: EntryViewModel, navController: NavController){
    var name by remember { mutableStateOf(selectedEntry.name) }
    var description by remember { mutableStateOf(selectedEntry.text) }
    var rating by remember {mutableStateOf(selectedEntry.rating.toString())}
    val detailsViewModel: EntryDetailsViewModel = viewModel()
    var expanded by remember { mutableStateOf(false) }

    val ratingData = List(10) { (it + 1).toString() }

    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        name?.let { it1 ->
            TextField(
                value = it1,
                onValueChange = {name=it},
                label = {Text("Name:")},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Clear Rating",
                        modifier= Modifier.clickable{
                            name = ""
                        }
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            description?.let {
                TextField(
                    value = it,
                    onValueChange = { description = it },
                    label = { Text("Description of the day") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear Rating",
                            modifier = Modifier.clickable {
                                description = ""
                            }
                        )
                    }
                )
            }
            IconButton(onClick = { detailsViewModel.readEntry(context, selectedEntry) }) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "Read Aloud")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Select Rating")
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                ratingData.forEach { option->
                    DropdownMenuItem(
                        text = {Text(option)},
                        onClick = {
                            rating = option
                            expanded = false
                        }
                    )

                }
            }
            Text("$rating/10")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    if (name?.isNotBlank() == true && description?.isNotBlank() == true){
                        detailsViewModel.editEntry(
                            entry = selectedEntry, description!!,
                            name!!,
                            rating)
                        Toast.makeText(
                            context,
                            "Entry number ${selectedEntry.id} edited!",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    }
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Entry")
            }

        }

    }
}