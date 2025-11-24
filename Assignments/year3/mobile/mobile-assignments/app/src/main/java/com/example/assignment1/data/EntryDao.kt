package com.example.assignment1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment1.models.Entry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface EntryDao {
    @Insert
    suspend fun insertEntry(entry: Entry)

    @Query ("SELECT * FROM entry ORDER BY date ASC")
    fun getAllEntries(): Flow<List<Entry>>

    @Query ("SELECT * FROM entry WHERE date = :date ORDER BY date ASC")
    fun getEntriesFromDate(date: LocalDateTime): Flow<List<Entry>>

    @Query("DELETE FROM entry")
    suspend fun deleteAll()

    @Query("SELECT * FROM entry WHERE id = :id")
    suspend fun getEntryById(id: Int): Entry?

    @Query("DELETE FROM entry WHERE id = :id")
    suspend fun deleteEntryById(id: Int)

}