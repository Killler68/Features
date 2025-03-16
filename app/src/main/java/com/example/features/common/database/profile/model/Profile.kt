package com.example.features.common.database.profile.model

data class Profile(
    val id: Int,
    val userId: Int,
    val email: String,
    val name: String,
    val age: String,
    val city: String,
    val nationality: String
)



fun ProfileData.toProfile() = Profile(
    id, userId, email, name, age, city, nationality
)

