package com.example.features.profile.data.database.model

data class Profile(
    val id: Int,
    val userId: Int,
    val userLogin: String?,
    val email: String,
    val name: String,
    val age: String,
    val city: String,
    val nationality: String
)


fun ProfileData.toProfile() = Profile(
    id = id,
    userId = userId,
    userLogin = userLogin,
    email = email,
    name = name,
    age = age,
    city = city,
    nationality = nationality
)

