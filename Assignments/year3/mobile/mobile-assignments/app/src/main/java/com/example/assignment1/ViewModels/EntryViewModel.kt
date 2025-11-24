package com.example.assignment1.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.assignment1.models.Entry
import java.time.LocalDateTime

class EntryViewModel: ViewModel() {
    private val _entries = mutableStateListOf<Entry>()
    val entries: List<Entry> get() = _entries

    init {
        addEntry("What a beautiful day!", "John Doe", "9")
        addEntry("I learned a new skill today.", "Jane Smith", "8")
        addEntry("Feeling a bit tired.", "Peter Pan", "6")
        addEntry("Enjoyed a great meal with friends.", "Alice Johnson", "10")
        addEntry("Worked on a challenging project.", "Bob Williams", "7")
        addEntry("Read a fascinating book.", "Charlie Brown", "8")
        addEntry("Went for a long walk in the park.", "Diana Prince", "9")
        addEntry("It was a rainy and cozy day indoors.", "Eve Adams", "7")
        addEntry("Feeling productive and accomplished.", "Frank Miller", "9")
        addEntry("Tried a new recipe, it was delicious!", "Grace Lee", "10")
        addEntry("Had some cookies", "Daniel Soden", "9")
    }

    fun addEntry(text:String, name:String, rating:String){
        val newEntry = Entry(
            id = _entries.size + 1,
            text = text,
            name = name,
            date = LocalDateTime.now(),
            rating = rating.toInt(),
        )
        _entries.add(newEntry)
    }

    fun searchEntries(query: String): List<Entry> {
        val filteredEntries = if (query.isBlank()){
            _entries
        }else{
            _entries.filter { entry ->
                val textMatch = entry.text?.contains(query, ignoreCase = true) ?: false
                textMatch
            }
        }
        return filteredEntries
    }

    fun removeEntry(entry: Entry){
        _entries.remove(entry)
    }

}