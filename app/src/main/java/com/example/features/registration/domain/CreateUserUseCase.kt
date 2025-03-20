package com.example.features.registration.domain

import com.example.features.common.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(login: String, password: String): Int = withContext(Dispatchers.Default) {
        userRepository.createUser(login, password)
    }
}