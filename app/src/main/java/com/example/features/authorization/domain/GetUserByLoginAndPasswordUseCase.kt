package com.example.features.authorization.domain

import com.example.features.common.database.user.model.User
import com.example.features.common.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserByLoginAndPasswordUseCase(private val repository: UserRepository) { // useless use case

    suspend operator fun invoke(login: String, password: String): User? =
        withContext(Dispatchers.IO) { //useless here
            val user = repository.getUserByLoginAndPassword(login, password)
            user
        }
}