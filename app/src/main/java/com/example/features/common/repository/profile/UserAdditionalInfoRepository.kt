package com.example.features.common.repository.profile

import com.example.features.common.database.profile.model.UserAdditionalInfo

interface UserAdditionalInfoRepository {

    suspend fun getUsersAdditionalInfo(): List<UserAdditionalInfo>
    suspend fun getUsersAdditionalInfoById(userId: Int): UserAdditionalInfo?
    suspend fun createUserAdditionalInfo(
        userId: Int,
        email: String,
        name: String,
        age: String,
        city: String,
        nationality: String
    ): UserAdditionalInfo
    suspend fun updateUserAdditionalInfo(userAdditionalInfo: UserAdditionalInfo)
    suspend fun deleteUserAdditionalInfo(id: Int)
}