package com.example.features.profile.domain.usecase

import com.example.features.common.repository.UserRepository

class GetUserLoginUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Int): String =
        userRepository.getUserById(userId)?.login ?: ""
}
