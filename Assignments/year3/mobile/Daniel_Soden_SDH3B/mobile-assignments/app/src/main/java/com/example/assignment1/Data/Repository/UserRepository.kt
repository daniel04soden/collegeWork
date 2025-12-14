package com.example.assignment1.Data.Repository

import com.example.assignment1.Data.UserDao
import com.example.assignment1.Models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User): Long {
        return userDao.insert(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun findUserByEmail(email: String): User? {
        return userDao.findUserByEmail(email)
    }

    suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)
    }

    suspend fun updateUserJournalStreak(userId: Long, streak: Int, lastEntryDate: String) {
        userDao.updateUserJournalStreak(userId, streak, lastEntryDate)
    }
}
