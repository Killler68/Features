package com.example.features.registration.usecase

import com.example.features.common.database.user.model.User
import com.example.features.common.repository.UserRepository
import com.example.features.registration.viewmodel.CreateUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateUserUseCaseImpl(
    private val userRepository: UserRepository
) : CreateUserUseCase {

    override suspend fun invoke(login: String, password: String): User {
        return withContext(Dispatchers.Default) {
            userRepository.createUser(login, password)
        }
    }
}