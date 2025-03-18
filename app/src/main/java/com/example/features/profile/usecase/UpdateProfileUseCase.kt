package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.Profile
import com.example.features.common.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(profile: Profile) =
        withContext(Dispatchers.IO) {
            repository.updateProfile(profile)
        }
}