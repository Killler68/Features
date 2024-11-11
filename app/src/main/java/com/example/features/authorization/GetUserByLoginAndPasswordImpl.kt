package com.example.features.authorization

import com.example.features.common.database.user.model.User
import com.example.features.common.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserByLoginAndPasswordImpl(
    private val repository: UserRepository
) : GetUserByLoginAndPassword {

    override suspend fun invoke(login: String, password: String): User? =
        withContext(Dispatchers.IO) {
            val user = repository.getUserByLoginAndPassword(login, password)
            user
        }
}