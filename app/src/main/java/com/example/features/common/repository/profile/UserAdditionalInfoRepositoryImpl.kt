package com.example.features.common.repository.profile

import com.example.features.common.database.profile.UserAdditionalInfoDao
import com.example.features.common.database.profile.model.UserAdditionalInfo
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

    override suspend fun getUsersAdditionalInfoById(id: Int): UserAdditionalInfo =
        withContext(Dispatchers.IO) {
            val userAdditionalInfo =
                userAdditionalInfoDao.getUserAdditionalInfoById(id)?.toUserAdditionalInfo()
            userAdditionalInfo ?: throw Exception("UserAdditionalInfo $id is not Found ")

        }

    override suspend fun createUserAdditionalInfo(
        email: String,
        name: String,
        age: String,
        city: String,
        nationality: String
    ): UserAdditionalInfo =
        withContext(Dispatchers.IO) {
            userAdditionalInfoDao.createUserAdditionalInfo(
                CreateUserAdditionalInfoTuple(
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

    override suspend fun deleteUserAdditionalInfo(id: Int) =
        withContext(Dispatchers.IO) {
            userAdditionalInfoDao.deleteUserAdditionalInfo(
                DeleteUserAdditionalInfoTuple(id)
            )
        }
}