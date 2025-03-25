package com.example.features.profile.domain.usecase

import com.example.features.profile.data.database.model.Profile
import com.example.features.profile.data.repository.ProfileRepository

class GetProfileByIdUseCase(
    private val repository: ProfileRepository,
) {

    suspend operator fun invoke(userId: Int): Profile? = repository.getProfileById(userId)
}