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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.models.Entry
import java.time.format.DateTimeFormatter

@Composable
fun EntryScreen(viewModel: EntryViewModel=viewModel(), navController: NavController){
    Scaffold(
        topBar = {Text(text = "Journal Entries: Improvio")},
        content = {
            paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                AddEntry(viewModel)
                Spacer(modifier = Modifier.height(20.dp))
                ListOfEntries(viewModel, navController)
            }
        }

    )
}


@Composable
fun ListOfEntries(viewModel: EntryViewModel = viewModel(), navController: NavController){
    val itemLimit:Int = 10
    val listOfEntries = viewModel.entries.take(itemLimit)

    LazyColumn {
        items(listOfEntries){entry->
            EntryItem(
                entry = entry,
                onDelete = { viewModel.removeEntry(entry) },
                onClick = { navController.navigate("entryDetails/${entry.id}") }
            )
        }
    }

}

@Composable
fun EntryItem(entry: Entry, onDelete: () -> Unit, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
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
                Text(text = "Date: ${entry.date.format(DateTimeFormatter.ISO_DATE)}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "Rating: ${entry.rating.toString()}", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Name: ${entry.name}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {onDelete()}) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete Entry")
                }
            }
        }
    }
}

@Composable
fun AddEntry(viewModel: EntryViewModel) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }

    val context = LocalContext.current


    Column(
        modifier = Modifier.padding(40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Who is writing this entry?") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "Clear Rating",
                    modifier = Modifier.clickable {
                        name = ""
                    }
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("How was today?") },
            modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rate your day out 10") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "Clear Rating",
                    modifier = Modifier.clickable {
                        rating = ""
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    if (name.isNotBlank() && description.isNotBlank()) {
                        viewModel.addEntry(description, name, rating)
                        Toast.makeText(
                            context,
                            "Entry number ${viewModel.entries.size} added!",
                            Toast.LENGTH_SHORT
                        ).show()
                        name = ""
                        description = ""
                        rating = ""
                    }
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Entry")
            }
        }
    }
}


