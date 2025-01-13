package com.example.features.common.database.profile

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.features.common.database.profile.model.UserAdditionalInfo
import com.example.features.common.database.profile.model.UserAdditionalInfoData
import com.example.features.common.database.profile.tuple.CreateUserAdditionalInfoTuple
import com.example.features.common.database.profile.tuple.DeleteUserAdditionalInfoTuple

@Dao
interface UserAdditionalInfoDao {

    @Query("SELECT * FROM user_additional_info")
    suspend fun getUsersAdditionalInfo(): List<UserAdditionalInfoData>

    @Query("SELECT * FROM user_additional_info WHERE userId = :userId")
    suspend fun getUserAdditionalInfoById(userId: Int): UserAdditionalInfoData?

    @Insert(entity = UserAdditionalInfoData::class)
    suspend fun createUserAdditionalInfo(user: CreateUserAdditionalInfoTuple): Long

    @Delete(entity = UserAdditionalInfoData::class)
    suspend fun deleteUserAdditionalInfo(id: DeleteUserAdditionalInfoTuple)

    @Update
    suspend fun updateUserAdditionalInfo(userAdditionalInfo: UserAdditionalInfoData)
}