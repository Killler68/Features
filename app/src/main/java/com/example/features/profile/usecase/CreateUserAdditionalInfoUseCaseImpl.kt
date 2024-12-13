package com.example.features.profile.usecase

import com.example.features.common.database.profile.model.UserAdditionalInfo
import com.example.features.common.repository.profile.UserAdditionalInfoRepository
import com.example.features.profile.viewmodel.CreateUserAdditionalInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateUserAdditionalInfoUseCaseImpl(
    private val repository: UserAdditionalInfoRepository
) : CreateUserAdditionalInfoUseCase {

    override suspend fun invoke(
        email: String, name: String, age: String, city: String, nationality: String
    ): UserAdditionalInfo {
        return withContext(Dispatchers.IO) {
            repository.createUserAdditionalInfo(email, name, age, city, nationality)
        }
    }
}