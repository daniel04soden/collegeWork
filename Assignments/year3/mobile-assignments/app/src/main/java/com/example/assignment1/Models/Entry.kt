package com.example.assignment1.models

import java.time.LocalDateTime

data class Entry(
    val id:Int, // Unique identifier for each entry
    val text:String? = null, // Text entered by the user
    val name:String? = "", // User who entered the text
    val date: LocalDateTime, // Date of entry
    val time: String = date.toLocalTime().toString(), // Time of entry
    var rating:Int? = null  // Rating out of ten for the day
)
