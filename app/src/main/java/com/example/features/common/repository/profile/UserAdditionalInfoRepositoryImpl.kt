package com.example.features.common.repository.profile

import com.example.features.common.database.profile.UserAdditionalInfoDao
import com.example.features.common.database.profile.model.UserAdditionalInfo
import com.example.features.common.database.profile.model.UserAdditionalInfoData
import com.example.features.common.database.profile.model.toUserAdditionalInfo
import com.example.features.common.database.profile.tuple.CreateUserAdditionalInfoTuple
import com.example.features.common.database.profile.tuple.DeleteUserAdditionalInfoTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserAdditionalInfoRepositoryImpl(
    private val userAdditionalInfoDao: UserAdditionalInfoDao
) : UserAdditionalInfoRepository {

    override suspend fun getUsersAdditionalInfo(): List<UserAdditionalInfo> =
        withContext(Dispatchers.IO) {
            val userAdditionalInfo = userAdditionalInfoDao.getUsersAdditionalInfo()
            userAdditionalInfo.map { it.toUserAdditionalInfo() }.toList()
        }

    override suspend fun getUsersAdditionalInfoById(userId: Int): UserAdditionalInfo? =
        withContext(Dispatchers.IO) {
            val userAdditionalInfo =
                userAdditionalInfoDao.getUserAdditionalInfoById(userId)?.toUserAdditionalInfo()
            userAdditionalInfo

        }

    override suspend fun createUserAdditionalInfo(
        userId: Int,
        email: String,
        name: String,
        age: String,
        city: String,
        nationality: String
    ): UserAdditionalInfo =
        withContext(Dispatchers.IO) {
            userAdditionalInfoDao.createUserAdditionalInfo(
                CreateUserAdditionalInfoTuple(
                    userId = userId,
                    email = email,
                    name = name,
                    age = age,
                    city = city,
                    nationality = nationality
                )
            )
            val createUserAdditionalInfo =
                userAdditionalInfoDao.getUsersAdditionalInfo().last().toUserAdditionalInfo()
            createUserAdditionalInfo
        }

    override suspend fun updateUserAdditionalInfo(userAdditionalInfo: UserAdditionalInfo) {
        userAdditionalInfoDao.updateUserAdditionalInfo(
            UserAdditionalInfoData(
                id = userAdditionalInfo.id,
                userId = userAdditionalInfo.userId,
                email = userAdditionalInfo.email,
                name = userAdditionalInfo.name,
                age = userAdditionalInfo.age,
                city = userAdditionalInfo.city,
                nationality = userAdditionalInfo.nationality
            )
        )
    }

    override suspend fun deleteUserAdditionalInfo(id: Int) =
        withContext(Dispatchers.IO) {
            userAdditionalInfoDao.deleteUserAdditionalInfo(
                DeleteUserAdditionalInfoTuple(id)
            )
        }
}