package com.example.assignment1.ViewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.assignment1.models.Entry
import java.time.LocalDate

class EntryViewModel: ViewModel() {
    private val _entries = mutableStateListOf<Entry>()
    val entries: List<Entry> get() = _entries

    @RequiresApi(Build.VERSION_CODES.O)
    fun addEntry(text:String, name:String, rating:String){
        val newEntry = Entry(
            id = _entries.size + 1,
            text = text,
            user = name,
            date = LocalDate.now(),
            rating = rating.toInt(),
        )
        _entries.add(newEntry
        )
    }
    fun removeEntry(entry: Entry){
        _entries.remove(entry)
    }
}