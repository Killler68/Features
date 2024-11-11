package com.example.features.common.database.user.model

data class User(
    val id: Int = 0,
    val login: String,
    val password: String
)

val emptyUser = User(0, "", "")

fun UserData.toUser() = User(
    id, login, password
)
