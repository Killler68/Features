package com.example.features.profile.domain.usecase

import com.example.features.profile.data.database.model.Profile
import com.example.features.profile.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateProfileUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(profile: Profile): Profile = repository.addProfile(profile)
}