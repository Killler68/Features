package com.example.features.profile.viewmodel

import com.example.features.common.database.profile.model.Profile

interface CreateProfileUseCase {

    suspend operator fun invoke(profile: Profile): Profile
}