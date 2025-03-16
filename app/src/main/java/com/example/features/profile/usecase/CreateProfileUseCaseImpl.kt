package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.Profile
import com.example.features.common.repository.profile.ProfileRepository
import com.example.features.profile.viewmodel.CreateProfileUseCase

class CreateProfileUseCaseImpl(
    private val repository: ProfileRepository
) : CreateProfileUseCase {

    override suspend operator fun invoke(profile: Profile): Profile {
        return repository.addProfile(profile)
    }
}