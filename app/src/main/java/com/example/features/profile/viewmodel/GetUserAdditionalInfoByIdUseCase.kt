package com.example.features.profile.viewmodel

import com.example.features.common.database.profile.model.UserAdditionalInfo

interface GetUserAdditionalInfoByIdUseCase {

    suspend operator fun invoke(id: Int): UserAdditionalInfo
}