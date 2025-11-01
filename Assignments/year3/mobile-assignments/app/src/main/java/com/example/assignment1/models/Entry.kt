package com.example.assignment1.models

import java.util.Date

class Entry(val date: Date, val user: User, val text: String, val rating: Int) {
    override fun toString(): String {
        return "$date ${user.name}: $text"
    }

    fun getRatingAsString(): String {
       return "$rating/10"
    }
}
