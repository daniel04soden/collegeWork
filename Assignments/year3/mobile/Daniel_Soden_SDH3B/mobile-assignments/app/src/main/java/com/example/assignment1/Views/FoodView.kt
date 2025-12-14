package com.example.assignment1.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assignment1.Models.Food
import com.example.assignment1.ViewModels.FoodViewModel
import com.example.assignment1.ViewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(viewModel: FoodViewModel = viewModel(), userViewModel: UserViewModel = viewModel()) {
    var optionsExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var calorieFilter by remember { mutableStateOf<String?>("") }
    var searchQuery by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val lazyPagingItems = viewModel.itemsFlow.collectAsLazyPagingItems()
    val currentUser by userViewModel.currentUser.collectAsState()

    LaunchedEffect(calorieFilter) {
        viewModel.setCalorieFilter(calorieFilter?.toFloatOrNull())
    }

    LaunchedEffect(searchQuery) {
        viewModel.setSearchQuery(searchQuery)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Food") },
                actions = {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Button(onClick = { viewModel.setCustomFoodFilter(true) }) {
                                Text("Custom")
                            }
                            Button(onClick = { viewModel.setCustomFoodFilter(false) }) {
                                Text("Default")
                            }
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Food")
            }
        },
        content = { paddingValues ->
            if (showDialog) {
                var foodName by remember { mutableStateOf("") }
                var calories by remember { mutableStateOf("") }
                var protein by remember { mutableStateOf("") }
                var fat by remember { mutableStateOf("") }
                var carbs by remember { mutableStateOf("") }
                var sugar by remember { mutableStateOf("") }
                var fiber by remember { mutableStateOf("") }

                AddFoodDialog(
                    onDismiss = { showDialog = false },
                    onConfirm = {
                        val newFood = Food(
                            productName = "$foodName (Custom)",
                            energy100G = calories.toDouble(),
                            proteinOneHundredG = protein.toDouble(),
                            fatOneHundredG = fat.toDouble(),
                            carboHyOneHundredG = carbs.toDouble(),
                            sugarOneHundredG = sugar.toDouble(),
                            fiberOneHundredG = fiber.toDouble()
                        )
                        viewModel.addNewFood(newFood)
                        showDialog = false
                    },
                    foodName = foodName,
                    onFoodNameChange = { foodName = it },
                    calories = calories,
                    onCaloriesChange = { calories = it },
                    protein = protein,
                    onProteinChange = { protein = it },
                    fat = fat,
                    onFatChange = { fat = it },
                    carbs = carbs,
                    onCarbsChange = { carbs = it },
                    sugar = sugar,
                    onSugarChange = { sugar = it },
                    fiber = fiber,
                    onFiberChange = { fiber = it }
                )
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                currentUser?.let { 
                    WelcomeCard(user = it, activity = "Food")
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Calories: ${it.caloriesConsumedToday} / ${it.caloriesPerDay}")
                        Text(text = "Protein: ${it.proteinConsumedToday}g")
                    }
                }
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                LazyColumn {
                    items(lazyPagingItems.itemCount) { index ->
                        val item = lazyPagingItems[index]
                        if (item != null) {
                            FoodItemCard(food = item, onAddClick = { viewModel.addFoodToUser(item) })
                        }
                    }

                    lazyPagingItems.loadState.apply {
                        when {
                            refresh is LoadState.Loading -> {
                                item {
                                    Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                            append is LoadState.Loading -> {
                                item {
                                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                            refresh is LoadState.Error -> {
                                item {
                                    Text("Error: " + (refresh as LoadState.Error).error.localizedMessage)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun AddFoodDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    foodName: String,
    onFoodNameChange: (String) -> Unit,
    calories: String,
    onCaloriesChange: (String) -> Unit,
    protein: String,
    onProteinChange: (String) -> Unit,
    fat: String,
    onFatChange: (String) -> Unit,
    carbs: String,
    onCarbsChange: (String) -> Unit,
    sugar: String,
    onSugarChange: (String) -> Unit,
    fiber: String,
    onFiberChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Food") },
        text = {
            Column {
                TextField(value = foodName, onValueChange = onFoodNameChange, label = { Text("Food Name") })
                TextField(value = calories, onValueChange = onCaloriesChange, label = { Text("Calories") })
                TextField(value = protein, onValueChange = onProteinChange, label = { Text("Protein") })
                TextField(value = fat, onValueChange = onFatChange, label = { Text("Fat") })
                TextField(value = carbs, onValueChange = onCarbsChange, label = { Text("Carbs") })
                TextField(value = sugar, onValueChange = onSugarChange, label = { Text("Sugar") })
                TextField(value = fiber, onValueChange = onFiberChange, label = { Text("Fiber") })
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Add")
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
fun FoodItemCard(food: Food, onAddClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${food.productName}")
            Text(text = "Calories: ${food.energy100G}")
            Text(text = "Protein: ${food.proteinOneHundredG}g")
            Button(onClick = onAddClick) {
                Text("Add")
            }
        }
    }
}
