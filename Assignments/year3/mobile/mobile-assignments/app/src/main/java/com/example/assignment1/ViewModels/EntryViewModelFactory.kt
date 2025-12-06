package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment1.Data.EntryDao

class EntryViewModelFactory(
    private val entryDao: EntryDao,
    private val userViewModel: UserViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntryViewModel(entryDao, userViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
