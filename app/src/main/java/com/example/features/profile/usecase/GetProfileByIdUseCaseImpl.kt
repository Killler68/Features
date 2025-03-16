package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.Profile
import com.example.features.common.repository.profile.ProfileRepository
import com.example.features.profile.viewmodel.GetProfileByIdUseCase

class GetProfileByIdUseCaseImpl(
    private val repository: ProfileRepository
) : GetProfileByIdUseCase {
    override suspend operator fun invoke(userId: Int): Profile? {
        return repository.getProfileById(userId)
    }
}