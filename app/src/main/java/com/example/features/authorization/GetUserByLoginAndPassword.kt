package com.example.features.authorization

import com.example.features.common.database.user.model.User

interface GetUserByLoginAndPassword {

    suspend operator fun invoke(login: String, password: String): User?
}