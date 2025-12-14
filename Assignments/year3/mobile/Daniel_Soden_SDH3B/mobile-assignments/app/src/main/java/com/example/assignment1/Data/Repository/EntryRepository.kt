package com.example.assignment1.Data.Repository

import com.example.assignment1.Data.EntryDao
import com.example.assignment1.Models.Entry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class EntryRepository(private val entryDao: EntryDao) {
    fun getAllEntries(userId: Long): Flow<List<Entry>> = entryDao.getAllEntries(userId)

    suspend fun insertEntry(entry: Entry) {
        entryDao.insertEntry(entry)
    }

    suspend fun deleteEntryById(entryId: Long, userId: Long) {
        entryDao.deleteEntryById(entryId, userId)
    }

    suspend fun updateEntry(entryId: Long?, newText: String, newRating: Int, userId: Long) {
        if (entryId != null) {
            entryDao.updateEntry(
                id = entryId,
                newText = newText,
                newRating = newRating,
                userId = userId
            )
        }
    }

    suspend fun getMostRecentEntry(userId: Long): Entry? {
        return entryDao.getMostRecentEntry(userId)
    }

    suspend fun getEntriesForToday(userId: Long, startOfDay: LocalDateTime, endOfDay: LocalDateTime): List<Entry> {
        return entryDao.getEntriesForToday(userId, startOfDay, endOfDay)
    }

    suspend fun getEntryById(entryId: Long, userId: Long): Entry? {
        return entryDao.getEntryById(entryId, userId)
    }
}
