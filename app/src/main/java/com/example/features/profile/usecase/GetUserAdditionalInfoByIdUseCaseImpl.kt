package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.UserAdditionalInfo
import com.example.features.common.repository.profile.UserAdditionalInfoRepository
import com.example.features.profile.viewmodel.GetUserAdditionalInfoByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserAdditionalInfoByIdUseCaseImpl(
    private val repository: UserAdditionalInfoRepository
) : GetUserAdditionalInfoByIdUseCase {
    override suspend fun invoke(id: Int): UserAdditionalInfo = withContext(Dispatchers.IO) {
        val user = repository.getUsersAdditionalInfoById(id)
        user
    }
}