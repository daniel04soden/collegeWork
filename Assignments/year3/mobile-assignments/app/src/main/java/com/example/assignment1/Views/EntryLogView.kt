package com.example.assignment1.Views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.EntryViewModel
import com.example.assignment1.models.Entry
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen(viewModel: EntryViewModel = viewModel(), navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var searchQ by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Journal Entries") },
            )},
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { isSheetOpen = true },
                text = { Text("Quick add") },
                icon = { Icon(Icons.Filled.Add, "Add Entry") },
                expanded = listState.firstVisibleItemIndex == 0
            )
        },
        content = { paddingValues ->
            Column(Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                TextField(
                    value = searchQ,
                    onValueChange = { searchQ = it },
                    modifier = Modifier.fillMaxWidth().padding(16.dp,8.dp),
                    label = { Text("Search") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                                  },
                    singleLine = true
                )
                EntireListOfEntries(
                    viewModel = viewModel,
                    listState = listState,
                    searchQuery = searchQ
                )
            }
            if (isSheetOpen) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { isSheetOpen = false },
                ) {
                    AddEntrySheet(
                        viewModel = viewModel,
                        onAddEntry = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    isSheetOpen = false
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun AddEntrySheet(viewModel: EntryViewModel, onAddEntry: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Add New Log", style = MaterialTheme.typography.headlineSmall)
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Who is writing this entry?") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("How was today?") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rate your day out of 10") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (name.isNotBlank() && description.isNotBlank() && rating.isNotBlank()) {
                    viewModel.addEntry(description, name, rating)
                    Toast.makeText(context, "Entry added!", Toast.LENGTH_SHORT).show()
                    onAddEntry()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add")
        }
    }
}


@Composable
fun LogItem(entry: Entry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.name ?: "Untitled",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Rating: ${entry.rating}/10",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = "Date: ${entry.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            entry.text?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun EntireListOfEntries(
    viewModel: EntryViewModel = viewModel(),
    listState: LazyListState,
    searchQuery: String
) {
    val filteredEntries = if (searchQuery.isBlank()){
        viewModel.entries
    }else{
        viewModel.entries.filter { entry ->
            val textMatch = entry.text?.contains(searchQuery, ignoreCase = true) ?: false
            textMatch
        }
    }
    LazyColumn(state = listState) {
        items(filteredEntries) { entry ->
            LogItem(
                entry = entry,
            )
        }
    }

}
