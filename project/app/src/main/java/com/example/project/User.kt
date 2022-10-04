package com.example.project

data class User(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    var activityLevel: Int,
    val isMale: Boolean,
    val location: String,
    val imagePath: String
)