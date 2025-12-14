package com.example.assignment1.Data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment1.Models.Entry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface EntryDao {
    @Insert
    suspend fun insertEntry(entry: Entry)

    @Insert
    suspend fun insertAll(vararg entries: Entry)

    @Query("SELECT * FROM entry WHERE userId = :userId ORDER BY date ASC")
    fun getAllEntries(userId: Long): Flow<List<Entry>>
    @Query("SELECT * FROM entry WHERE userId = :userId ORDER BY date DESC LIMIT 1")
    suspend fun getMostRecentEntry(userId: Long): Entry?

    @Query("SELECT * FROM entry WHERE userId = :userId AND date >= :startOfDay AND date <= :endOfDay")
    suspend fun getEntriesForToday(userId: Long, startOfDay: LocalDateTime, endOfDay: LocalDateTime): List<Entry>
    @Query("SELECT * FROM entry WHERE date = :date AND userId = :userId ORDER BY date ASC")
    fun getEntriesFromDate(date: String, userId: Long): Flow<List<Entry>>

    @Query("DELETE FROM entry WHERE userId = :userId")
    suspend fun deleteAllEntriesForUser(userId: Long)

    @Query("SELECT * FROM entry WHERE id = :id AND userId = :userId")
    suspend fun getEntryById(id: Long, userId: Long): Entry?

    @Query("DELETE FROM entry WHERE id = :id AND userId = :userId")
    suspend fun deleteEntryById(id: Long, userId: Long)

    @Query("UPDATE entry SET text = :newText, rating = :newRating WHERE id = :id AND userId = :userId")
    suspend fun updateEntry(id: Long?, newText: String, newRating: Int, userId: Long)
}
