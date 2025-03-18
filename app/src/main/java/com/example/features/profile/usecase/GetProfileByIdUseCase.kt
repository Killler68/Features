package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.Profile
import com.example.features.common.repository.profile.ProfileRepository

class GetProfileByIdUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(userId: Int): Profile? = repository.getProfileById(userId)
}