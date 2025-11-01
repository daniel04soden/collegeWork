package com.example.assignment1.models

data class User(
    val name: String,
    val age: Int,
    val email: String,
    val password: String,
    val height: Double,
    val weight: Double
) {
    override fun toString(): String {
        return "User [name=$name, age=$age, email=$email, password=$password, height=$height, weight=$weight]"
    }
}