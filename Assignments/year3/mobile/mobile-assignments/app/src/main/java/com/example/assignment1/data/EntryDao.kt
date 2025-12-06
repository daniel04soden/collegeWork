package com.example.assignment1.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment1.Models.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Insert
    suspend fun insertEntry(entry: Entry)

    @Query ("SELECT * FROM entry ORDER BY date ASC")
    fun getAllEntries(): Flow<List<Entry>>

    @Query ("SELECT * FROM entry WHERE date = :date ORDER BY date ASC")
    fun getEntriesFromDate(date: String): Flow<List<Entry>>

    @Query("DELETE FROM entry")
    suspend fun deleteAll()

    @Query("SELECT * FROM entry WHERE id = :id")
    suspend fun getEntryById(id: Int): Entry?

    @Query("DELETE FROM entry WHERE id = :id")
    suspend fun deleteEntryById(id: Int)
    @Query ("UPDATE entry SET text = :newText, rating = :newRating WHERE id = :id")
    suspend fun updateEntry(id: Int?, newText: String, newRating: Int)
}