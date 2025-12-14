package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment1.Data.Repository.MeditationRepository

class MeditationViewModelFactory(
    private val userViewModel: UserViewModel,
    private val repository: MeditationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeditationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MeditationViewModel(userViewModel, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
