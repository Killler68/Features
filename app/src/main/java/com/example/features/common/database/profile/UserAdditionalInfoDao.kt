package com.example.features.common.database.profile

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.features.common.database.profile.model.UserAdditionalInfoData
import com.example.features.common.database.profile.tuple.CreateUserAdditionalInfoTuple
import com.example.features.common.database.profile.tuple.DeleteUserAdditionalInfoTuple

@Dao
interface UserAdditionalInfoDao {

    @Query("SELECT * FROM user_additional_info")
    suspend fun getUsersAdditionalInfo(): List<UserAdditionalInfoData>

    @Query("SELECT * FROM user_additional_info WHERE id = :id")
    suspend fun getUserAdditionalInfoById(id: Int): UserAdditionalInfoData?

    @Insert(entity = UserAdditionalInfoData::class)
    suspend fun createUserAdditionalInfo(user: CreateUserAdditionalInfoTuple): Long

    @Delete(entity = UserAdditionalInfoData::class)
    suspend fun deleteUserAdditionalInfo(id: DeleteUserAdditionalInfoTuple)
}