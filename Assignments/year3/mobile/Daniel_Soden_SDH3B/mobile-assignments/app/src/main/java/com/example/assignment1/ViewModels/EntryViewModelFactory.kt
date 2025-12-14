package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment1.Data.Repository.EntryRepository
import com.example.assignment1.Data.Repository.UserRepository

class EntryViewModelFactory(private val entryRepository: EntryRepository, private val userRepository: UserRepository, private val userId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntryViewModel(entryRepository, userRepository, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
