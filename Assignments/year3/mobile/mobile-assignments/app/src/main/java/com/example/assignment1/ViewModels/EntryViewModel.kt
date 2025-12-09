package com.example.assignment1.ViewModels

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.Data.EntryDao
import com.example.assignment1.Models.Entry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Locale

class EntryViewModel(private val entryDao: EntryDao,val userViewModel: UserViewModel) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _allEntries: Flow<List<Entry>> = userViewModel.currentUser.flatMapLatest { user ->
        if (user != null) {
            entryDao.getAllEntries(user.id)
        } else {
            MutableStateFlow(emptyList())
        }
    }

    val entries: Flow<List<Entry>> = searchQuery.combine(_allEntries) { query, entries ->
        if (query.isBlank()) {
            entries
        } else {
            entries.filter { entry ->
                entry.text?.contains(query, ignoreCase = true) == true
            }
        }
    }.stateIn( // Convert the Flow to a StateFlow
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), // Start collecting when the UI is active
        initialValue = emptyList() // Initial value while the flow is being prepared
    )

    private val _selectedEntry = MutableStateFlow<Entry?>(null)
    val selectedEntry = _selectedEntry.asStateFlow()

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

// In com/example/assignment1/ViewModels/EntryViewModel.kt

    suspend fun addEntry(text: String, rating: String): Boolean {
        val userDao = userViewModel.userDao
        val currentUser = userViewModel.currentUser.value ?: return false
        val currentUserId = currentUser.id
        val todayDate = LocalDate.now()
        val startOfDay = todayDate.atStartOfDay()
        val endOfDay = todayDate.atTime(LocalTime.MAX)

        val entriesFromToday = entryDao.getEntriesForToday(currentUserId, startOfDay, endOfDay)

        if (entriesFromToday.isNotEmpty()) {
            println("Entry already done today")
            return false
        }

        val lastEntry = entryDao.getMostRecentEntry(currentUserId)
        var currentStreak = currentUser.journalStreak ?: 0

        if (lastEntry != null) {
            val lastEntryDate = lastEntry.date.toLocalDate()
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
            name = currentUser.fullName,
            date = LocalDateTime.now(),
            rating = rating.toIntOrNull() ?: 0,
            userId = currentUserId
        )
        entryDao.insertEntry(newEntry)

        val updatedUser = currentUser.copy(journalStreak = currentStreak)
        userDao.updateUser(updatedUser)

        return true
    }

    fun removeEntry(entry: Entry) {
        viewModelScope.launch {
            entryDao.deleteEntryById(entry.id, userViewModel.currentUser.value?.id ?: 0)
        }
    }

    fun editEntry(entry: Entry?, newText: String,newRating: String) {
        viewModelScope.launch {
            entryDao.updateEntry(
                entry?.id,
                newText,
                newRating = newRating.toIntOrNull() ?: 0,
                userId = userViewModel.currentUser.value?.id ?: 0
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

    fun findEntryById(entryId: Long): Entry? {
        viewModelScope.launch {
            _selectedEntry.value = entryDao.getEntryById(
                entryId,
                userViewModel.currentUser.value?.id ?: 0
            )
        }
        return _selectedEntry.value
    }

    fun clearSelectedEntry(){
        _selectedEntry.value = null
    }

    override fun onCleared() {
        super.onCleared()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}
