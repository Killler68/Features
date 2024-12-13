package com.example.features.profile.viewmodel

import com.example.features.common.database.profile.model.UserAdditionalInfo

interface CreateUserAdditionalInfoUseCase {

    suspend operator fun invoke(
        email: String,
        name: String,
        age: String,
        city: String,
        nationality: String
    ): UserAdditionalInfo
}