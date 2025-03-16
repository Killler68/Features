package com.example.features.common.database.profile

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.features.common.database.profile.model.ProfileData
import com.example.features.common.database.profile.tuple.CreateProfileTuple
import com.example.features.common.database.profile.tuple.DeleteProfileTuple

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    suspend fun getProfileDao(): List<ProfileData>

    @Query("SELECT * FROM profile WHERE userId = :userId")
    suspend fun getProfileByUserId(userId: Int): ProfileData?

    @Insert(entity = ProfileData::class)
    suspend fun createProfile(user: CreateProfileTuple): Long

    @Delete(entity = ProfileData::class)
    suspend fun deleteProfile(id: DeleteProfileTuple)

    @Update
    suspend fun updateProfile(profileData: ProfileData)
}