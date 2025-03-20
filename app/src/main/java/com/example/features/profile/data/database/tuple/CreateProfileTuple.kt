package com.example.features.profile.data.database.tuple

data class CreateProfileTuple(
    val userId: Int,
    val email: String,
    val name: String,
    val age: String,
    val city: String,
    val nationality: String
)
