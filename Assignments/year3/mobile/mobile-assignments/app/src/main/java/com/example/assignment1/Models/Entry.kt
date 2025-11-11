package com.example.assignment1.models

import java.time.LocalDateTime

data class Entry(
    val id:Int, // Unique identifier for each entry
    var text:String? = null, // Text entered by the user
    var name:String? = "", // User who entered the text
    var date: LocalDateTime, // Date of entry
    var time: String = date.toLocalTime().toString(), // Time of entry
    var rating:Int? = null,  // Rating out of ten for the day
)
