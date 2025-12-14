package com.example.assignment1.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.assignment1.Models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Insert
    suspend fun insertAllUsers(vararg user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun findUserByEmail(email: String): User?

    @Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Long): User?

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE user SET journalStreak = :streak, lastJournalEntryDate = :lastEntryDate WHERE id = :userId")
    suspend fun updateUserJournalStreak(userId: Long, streak: Int, lastEntryDate: String)
}
