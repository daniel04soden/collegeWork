package com.example.assignment1.models

import java.time.LocalDate
import java.util.Date

data class Entry(
    val id:Int, // Unique identifier for each entry
    val text:String, // Text entered by the user
    val user:String, // User who entered the text
    val date: LocalDate, // Date of entry
    val rating:Int  // Rating out of ten for the day
)
