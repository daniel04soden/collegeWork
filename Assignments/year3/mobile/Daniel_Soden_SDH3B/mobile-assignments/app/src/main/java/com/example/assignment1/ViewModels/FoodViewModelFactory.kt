package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment1.Data.Repository.FoodRepository
import com.example.assignment1.Data.Repository.UserRepository

class FoodViewModelFactory(
    private val foodRepository: FoodRepository,
    private val userRepository: UserRepository,
    private val userViewModel: UserViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(foodRepository, userRepository, userViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}