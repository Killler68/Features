package com.example.features.settings.usecase

import com.example.features.common.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserByIdUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(userId: Int) = withContext(Dispatchers.IO) {
        repository.getUserById(userId)
    }
}