package com.example.features.common.database.profile.tuple

data class CreateUserAdditionalInfoTuple(
    val userId: Int,
    val email: String,
    val name: String,
    val age: String,
    val city: String,
    val nationality: String
)
