package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment1.Data.UserDao
import com.example.assignment1.SessionManager

class UserViewModelFactory(private val userDao: UserDao,private val sessionManager: SessionManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao,sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

