package com.example.features.registration.usecase

import com.example.features.common.database.user.model.User
import com.example.features.common.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserByLoginUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(login: String): User? =
        withContext(Dispatchers.IO) {
            val user = repository.getUserByLogin(login)
            user
        }
}
