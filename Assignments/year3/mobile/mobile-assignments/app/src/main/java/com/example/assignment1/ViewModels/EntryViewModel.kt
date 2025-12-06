package com.example.assignment1.ViewModels

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.Data.EntryDao
import com.example.assignment1.Models.Entry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Locale

class EntryViewModel(private val entryDao: EntryDao,val userViewModel: UserViewModel) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _allEntries: Flow<List<Entry>> = entryDao.getAllEntries()

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

    fun addEntry(text: String,rating: String) {
        viewModelScope.launch {
            val newEntry = Entry(
                text = text,
                name = userViewModel.currentUser.value?.fullName,
                date = LocalDateTime.now(),
                rating = rating.toIntOrNull() ?: 0,
                userId = userViewModel.currentUser.value?.id
            )
            entryDao.insertEntry(newEntry)
        }
    }

    fun removeEntry(entry: Entry) {
        viewModelScope.launch {
            entryDao.deleteEntryById(entry.id)
        }
    }

    fun editEntry(entry: Entry?, newText: String,newRating: String) {
        viewModelScope.launch {
            entryDao.updateEntry(
                entry?.id,
                newText,
                newRating = newRating.toIntOrNull() ?: 0
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

    fun findEntryById(entryId: Int): Entry? {
        viewModelScope.launch {
            _selectedEntry.value = entryDao.getEntryById(entryId)
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
