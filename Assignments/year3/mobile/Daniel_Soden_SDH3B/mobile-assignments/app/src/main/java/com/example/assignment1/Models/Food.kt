package com.example.assignment1.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // Unique identifier for each food item - using index given in file
    val productName: String, // Name of food
    val energy100G: Double, // Cals per 100g
    val fatOneHundredG: Double, // Fat per 100g
    val carboHyOneHundredG: Double, // Carbs per 100g
    val sugarOneHundredG: Double, // Sugar per 100g
    val fiberOneHundredG: Double, // fiber per 100g
    val proteinOneHundredG: Double, // protein per 100g
    val servingSize: Double = 1.0 // All have 100grams as serving size
)
