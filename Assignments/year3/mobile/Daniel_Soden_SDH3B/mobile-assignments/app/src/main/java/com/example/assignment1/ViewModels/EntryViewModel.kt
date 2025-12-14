package com.example.assignment1.ViewModels

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.Data.Repository.EntryRepository
import com.example.assignment1.Data.Repository.UserRepository
import com.example.assignment1.Models.Entry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
class EntryViewModel(
    private val entryRepository: EntryRepository,
    private val userRepository: UserRepository,
    private val userId: Long
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val entries: StateFlow<List<Entry>> = entryRepository.getAllEntries(userId)
        .combine(searchQuery) { entries, query ->
            if (query.isBlank()) {
                entries
            } else {
                entries.filter { entry ->
                    entry.text?.contains(query, ignoreCase = true) == true
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _selectedEntry = MutableStateFlow<Entry?>(null)
    val selectedEntry = _selectedEntry.asStateFlow()

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    suspend fun addEntry(text: String, rating: String): Boolean { //TODO Fix bug with streak not incrementing until next day
        if (userId <= 0) {
            println("Invalid userId: $userId, cannot add entry.")
            return false
        }

        val currentUser = userRepository.getUserById(userId)
        val todayDate = LocalDate.now()
        val startOfDay = todayDate.atStartOfDay()
        val endOfDay = todayDate.atTime(LocalTime.MAX)

        val entriesFromToday = entryRepository.getEntriesForToday(userId, startOfDay, endOfDay)

        if (entriesFromToday.isNotEmpty()) {
            println("Entry already done today")
            return false
        }

        val lastEntry = entryRepository.getMostRecentEntry(userId)
        var currentStreak = currentUser?.journalStreak ?: 0

        if (lastEntry != null && lastEntry.date != null) {
            val lastEntryDate = lastEntry.date!!.toLocalDate()
            val yesterdayDate = todayDate.minusDays(1)

            if (lastEntryDate.isEqual(yesterdayDate)) {
                currentStreak++
            } else if (!lastEntryDate.isEqual(todayDate)) {
                currentStreak = 1
            }
        } else {
            currentStreak = 1
        }

        val newEntry = Entry(
            text = text,
            name = currentUser?.fullName,
            date = LocalDateTime.now(),
            rating = rating.toIntOrNull() ?: 0,
            userId = userId,
            calories = currentUser?.caloriesConsumedToday

        )
        entryRepository.insertEntry(newEntry)

        userRepository.updateUserJournalStreak(userId, currentStreak, todayDate.toString())

        return true
    }


    fun removeEntry(entry: Entry) {
        viewModelScope.launch {
            entryRepository.deleteEntryById(entry.id, userId)
        }
    }

    fun editEntry(entry: Entry?, newText: String, newRating: String) {
        viewModelScope.launch {
            entryRepository.updateEntry(
                entry?.id,
                newText,
                newRating = newRating.toIntOrNull() ?: 0,
                userId = userId
            )
        }
    }

    private var textToSpeech: TextToSpeech? = null

    fun readEntry(context: Context, entry: Entry?) {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.UK
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        entry?.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }

    suspend fun findEntryById(entryId: Long): Entry? {
        return entryRepository.getEntryById(entryId, userId)
    }

    fun clearSelectedEntry() {
        _selectedEntry.value = null
    }

    override fun onCleared() {
        super.onCleared()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}
