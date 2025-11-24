package com.example.assignment1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
    import com.example.assignment1.Models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user ORDER BY username ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllUsersByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    fun findUserByEmail(email: String): User

    @Insert
    fun insertAllUsers(vararg users: User)

    @Delete
    fun deleteUser(user: User)
}
