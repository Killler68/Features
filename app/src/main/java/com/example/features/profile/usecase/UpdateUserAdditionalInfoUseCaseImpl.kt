package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.UserAdditionalInfo
import com.example.features.common.repository.profile.UserAdditionalInfoRepository
import com.example.features.profile.viewmodel.UpdateUserAdditionalInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateUserAdditionalInfoUseCaseImpl(
    private val repository: UserAdditionalInfoRepository
) : UpdateUserAdditionalInfoUseCase {
    override suspend fun invoke(userAdditionalInfo: UserAdditionalInfo) =
        withContext(Dispatchers.IO) {
            repository.updateUserAdditionalInfo(userAdditionalInfo)
        }
}