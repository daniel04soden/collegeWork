package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel

class FoodViewModel: ViewModel() {
    suspend fun addFood(
        productName: String,
        caloriesOneHundredG: Double,
        fatOneHundredG: Double,
        carboHyOneHundredG: Double,
        proteinOneHundredG: Double,
        fiberOneHundredG: Double,
    ){
        // val currentUserId = userViewModel.currentUser.value?.id ?: 0
        //
    }
}