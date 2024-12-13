package com.example.features.common.repository.profile

import com.example.features.common.database.profile.model.UserAdditionalInfo

interface UserAdditionalInfoRepository {

    suspend fun getUsersAdditionalInfo(): List<UserAdditionalInfo>
    suspend fun getUsersAdditionalInfoById(id: Int): UserAdditionalInfo
    suspend fun createUserAdditionalInfo(
        email: String,
        name: String,
        age: String,
        city: String,
        nationality: String
    ): UserAdditionalInfo
    suspend fun deleteUserAdditionalInfo(id: Int)
}