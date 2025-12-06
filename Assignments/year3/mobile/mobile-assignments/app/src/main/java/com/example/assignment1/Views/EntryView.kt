package com.example.assignment1.Views

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment1.Models.Entry
import com.example.assignment1.ViewModels.EntryViewModel
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen(viewModel: EntryViewModel = viewModel()) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var searchQ by remember { mutableStateOf("") }
    var optionsExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var ratingFilter by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Journal Entries") },
                actions = {
                    Text("Filter by rating")
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Box {
                            IconButton(onClick = { optionsExpanded = true }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                            }
                            OptionsDropDownMenu(
                                expanded = optionsExpanded,
                                onDismiss = {optionsExpanded = false},
                                onOptionSelected = { option ->
                                    Toast.makeText(context,
                                        "$option Clicked", Toast.LENGTH_SHORT).show()
                                    ratingFilter = option
                                },
                                options = List(10) { (it + 1).toString() }
                            )
                        }
                        Button(onClick ={
                            ratingFilter = ""
                        } ) {Text("Reset filter") }
                    }
                }
            )},
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { isSheetOpen = true },
                text = { Text("Quick add") },
                icon = { Icon(Icons.Filled.Add, "Add Entry") },
                expanded = listState.firstVisibleItemIndex == 0,
            )
        },
        content = { paddingValues ->
            Column(Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                TextField(
                    value = searchQ,
                    onValueChange = { searchQ = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp),
                    label = { Text("Search") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    singleLine = true
                )
                EntireListOfEntries(
                    viewModel = viewModel,
                    listState = listState,
                    searchQuery = searchQ,
                    ratingCategory = ratingFilter
                )
            }
            if (isSheetOpen) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { isSheetOpen = false },
                ) {
                    QuickAddEntry(
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
fun OptionsDropDownMenu(
    expanded:Boolean,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit,
    options: List<String>
){
    val context = LocalContext.current
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {onDismiss()}
    ) {
        options.forEach {option->
            DropdownMenuItem(
                text = {Text(option)},
                onClick = {
                    onOptionSelected(option)
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun QuickEditEntry(
    entry: Entry,
    viewModel: EntryViewModel,
    onDismiss: () -> Unit
) {
    var text by remember { mutableStateOf(entry.text ?: "") }
    var rating by remember { mutableStateOf(entry.rating ?: "") }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    val ratingData = List(10) { (it + 1).toString() }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Entry") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Description") }
                )
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

            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.editEntry(
                        entry,
                        text,
                        rating.toString(),
                    )
                    Toast.makeText(context, "Entry updated!",
                        Toast.LENGTH_SHORT).show()
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@Composable
fun QuickDeleteEntry(
    entry: Entry,
    viewModel: EntryViewModel,
    onDismiss: () -> Unit
) {
    val ratingData = List(10) { (it + 1).toString() }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Deleting Entry ${entry.id}") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Are you sure you want to delete entry ${entry.id}?!")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.removeEntry(entry)
                    onDismiss()
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun QuickAddEntry(viewModel: EntryViewModel, onAddEntry: () -> Unit) {
    var description by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    val ratingData = List(10) { (it + 1).toString() }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Add New Log", style = MaterialTheme.typography.headlineSmall)
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("How was today?") },
            modifier = Modifier.fillMaxWidth()
        )
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

        Button(
            onClick = {
                if (description.isNotBlank() && rating.isNotBlank()) {
                    viewModel.addEntry(
                        text=description,
                        rating = rating
                    )
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
fun LogItem(
    entry: Entry,
    viewModel: EntryViewModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit

) {
    var expanded by remember { mutableStateOf(false) }

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
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
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
                    Text(
                        text = "Date: ${entry.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Box {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Edit/Delete")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit button"
                            )},
                            onClick = {
                                onEditClick()
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = {Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete button"
                            )},
                            onClick = {
                                onDeleteClick()
                                expanded = false
                            }
                        )
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            entry.text?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}


@Composable
fun EntireListOfEntries(    viewModel: EntryViewModel = viewModel(),
                            listState: LazyListState,
                            searchQuery: String,
                            ratingCategory: String
) {
    var entryToEdit by remember { mutableStateOf<Entry?>(null) }
    var entryToDelete by remember { mutableStateOf<Entry?>(null) }

    entryToEdit?.let { entry ->
        QuickEditEntry(
            entry = entry,
            viewModel = viewModel,
            onDismiss = { entryToEdit = null }
        )
    }

    entryToDelete?.let { entry ->
        QuickDeleteEntry(
            entry = entry,
            viewModel = viewModel,
            onDismiss = { entryToDelete = null }
        )
    }

    LaunchedEffect(key1 = searchQuery) {
        viewModel.onSearchQueryChange(searchQuery)
    }

    val entries by viewModel.entries.collectAsState(initial = emptyList())

    val filteredEntries = if (ratingCategory.isBlank()) {
        entries
    } else {
        entries.filter { it.rating == ratingCategory.toIntOrNull() }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.animateContentSize()
    ) {
        items(
            items = filteredEntries,
            key = { it.id }
        ) { entry ->
            LogItem(
                entry = entry,
                viewModel = viewModel,
                onEditClick = { entryToEdit = entry },
                onDeleteClick = { entryToDelete = entry }
            )
        }
    }
}