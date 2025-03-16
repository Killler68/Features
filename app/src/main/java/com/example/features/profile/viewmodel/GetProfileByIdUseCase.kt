package com.example.features.profile.viewmodel

import com.example.features.common.database.profile.model.Profile

interface GetProfileByIdUseCase {

    suspend operator fun invoke(userId: Int): Profile?
}