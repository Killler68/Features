package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.Profile
import com.example.features.common.repository.profile.ProfileRepository

class CreateProfileUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(profile: Profile): Profile = repository.addProfile(profile)
}