package com.example.features.settings.usecase

import com.example.features.common.repository.UserRepository

class DeleteUserUseCase(
    private val repository: UserRepository,
) {

    suspend operator fun invoke(userId: Int) = repository.deleteUser(userId)
}