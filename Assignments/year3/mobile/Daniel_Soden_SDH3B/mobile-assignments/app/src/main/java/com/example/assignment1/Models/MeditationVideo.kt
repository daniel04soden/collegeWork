package com.example.assignment1.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meditation")
data class MeditationVideo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val youtubeUrl: String,
    val description: String
)
