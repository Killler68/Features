package com.example.features.settings.domain

import com.example.features.common.repository.UserRepository

class DeleteUserByIdUseCase(
    private val repository: UserRepository,
) {

    suspend operator fun invoke(userId: Int) = repository.deleteUser(userId)
}