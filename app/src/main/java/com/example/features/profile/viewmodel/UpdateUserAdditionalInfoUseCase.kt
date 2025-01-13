package com.example.features.profile.viewmodel

import com.example.features.common.database.profile.model.UserAdditionalInfo

interface UpdateUserAdditionalInfoUseCase {

    suspend operator fun invoke(userAdditionalInfo: UserAdditionalInfo)
}