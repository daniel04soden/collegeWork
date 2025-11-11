package com.example.assignment1.ViewModels

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment1.models.Entry
import java.util.Locale

class EntryDetailsViewModel : ViewModel() {
    private val currentEntry = MutableLiveData<Entry>()
    private var textToSpeech: TextToSpeech? = null

    fun getCurrentEntry(): LiveData<Entry> {
        return currentEntry
    }

    fun editEntry(entry: Entry, newText: String, newName: String, newRating: String): Entry {
        entry.text = newText
        entry.name = newName
        entry.rating = newRating.toInt()
        return entry
    }

    fun readEntry(context: Context, entry: Entry){
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.UK
                    txtToSpeech.setSpeechRate(1.0f)
                    entry.text?.let { textToSpeak ->
                        txtToSpeech.speak(
                            textToSpeak,
                            TextToSpeech.QUEUE_ADD,
                            null,
                            null
                        )
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}