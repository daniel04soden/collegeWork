package com.example.assignment1.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.assignment1.Models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user ORDER BY fullName ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllUsersByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun findUserByEmail(email: String): User

    @Update
    suspend fun updateUser(user:User)

    @Insert
    fun insertAllUsers(vararg users: User)

    @Delete
    fun deleteUser(user: User)
}
