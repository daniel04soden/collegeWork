package com.example.assignment1.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.assignment1.models.Entry
import java.time.LocalDateTime

class EntryViewModel: ViewModel() {
    private val _entries = mutableStateListOf<Entry>()
    val entries: List<Entry> get() = _entries

    fun addEntry(text:String, name:String, rating:String){
        val newEntry = Entry(
            id = _entries.size + 1,
            text = text,
            name = name,
            date = LocalDateTime.now(),
            rating = rating.toInt(),
        )
        _entries.add(newEntry
        )
    }

    fun removeEntry(entry: Entry){
        _entries.remove(entry)
    }

}