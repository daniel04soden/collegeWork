package com.example.assignment1.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var fullName: String,
    var age: Int,
    val gender: String, // "female" or "male"
    var email: String,
    var password: String,
    var weight: Double,
    var height: Double,
    var loseWeight: String, // "Y" or "N"
    var caloriesPerDay: Double,
    var caloriesConsumedToday: Double = 0.0,
    var proteinConsumedToday: Double = 0.0,
    var lastFoodEntryDate: String? = null,
    var lastJournalEntryDate: String? = null,
    var lastMeditationEntryDate: String? = null,
    var steps: Double? = 0.0,

    var calorieStreak: Int? = 0,
    var meditationStreak: Int? = 0,
    var journalStreak: Int? = 0,
    var consistencyScore: Int? = 0
) {
    constructor(
        fullName: String,
        age: Int,
        gender: String,
        email: String,
        password: String,
        weight: Double,
        height: Double,
        loseWeight: String
    ) : this(
        fullName = fullName,
        age = age,
        gender = gender,
        email = email,
        password = password,
        weight = weight,
        height = height,
        loseWeight = loseWeight,
        caloriesPerDay = calculateCaloriesPerDay(weight, height, age, gender, loseWeight)
    )

    companion object {
        fun calculateCaloriesPerDay(weight: Double, height: Double, age: Int, gender: String, loseWeight: String): Double {
            var res: Double = if (gender.lowercase() == "female") {
                10 * weight + 6.25 * height - 5 * age - 161
            } else {
                10 * weight + 6.25 * height - 5 * age + 5
            }

            if (loseWeight == "Y") {
                res -= 200
            } else {
                res += 200
            }
            return res
        }
    }
}
