package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.Profile
import com.example.features.common.repository.profile.ProfileRepository
import com.example.features.profile.viewmodel.UpdateProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateProfileUseCaseImpl(
    private val repository: ProfileRepository
) : UpdateProfileUseCase {
    override suspend fun invoke(profile: Profile) =
        withContext(Dispatchers.IO) {
            repository.updateProfile(profile)
        }
}