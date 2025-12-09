package com.example.assignment1.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "entry")
data class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // Unique identifier for each entry
    val userId: Long?, // Id attached to user
    var text: String? = null, // Text entered by the user
    val name: String?, // User who entered the text
    var date: LocalDateTime, // Date of entry
    var time: String = date.toLocalTime().toString(), // Time of entry
    var rating: Int? = null,  // Rating out of ten for the day
)
