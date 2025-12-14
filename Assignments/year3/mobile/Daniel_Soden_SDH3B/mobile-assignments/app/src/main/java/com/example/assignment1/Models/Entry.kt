package com.example.assignment1.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "entry",
    foreignKeys = [ // Foreign required to tie directly to entries to given user
        ForeignKey( // Hard specified compared to other classes as entries are only tied to one user
                    // whereas foods and meditations are tied to anyone
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // Unique identifier for each entry
    val userId: Long, // Id attached to user
    val text: String? = null, // Text entered by the user
    val name: String?, // User who entered the text
    val date: LocalDateTime?, // Date of entry
    val rating: Int? = null,  // Rating out of ten for the day
    val steps: Int? = null, // Steps taken for the day
    val calories: Double?  // Calories burned for the day
)
