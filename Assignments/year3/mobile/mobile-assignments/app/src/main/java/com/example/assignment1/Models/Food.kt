package com.example.assignment1.Models

data class Food(
    val id: Int, // Unique identifier for each food item - using index given in file
    val productName: String, // Name of food
    val caloriesOneHundredG: Double, // Cals per 100g
    val fatOneHundredG: Double, // Fat per 100g
    val carboHyOneHundredG: Double, // Carbs per 100g
    val proteinOneHundredG: Double, // protein per 100g
    val fiberOneHundredG: Double, // fiber per 100g
    var servingSize: Double = 100.0
)
