package com.example.features.registration.viewmodel

import com.example.features.common.database.user.model.User

interface CreateUserUseCase {

    suspend operator fun invoke(login: String, password: String): User
}