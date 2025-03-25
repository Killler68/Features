package com.example.features.profile.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.features.profile.data.database.model.ProfileData
import com.example.features.profile.data.database.tuple.DeleteProfileTuple

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    suspend fun getProfileDao(): List<ProfileData>

    @Query("SELECT * FROM profile WHERE userId = :userId")
    suspend fun getProfileByUserId(userId: Int): ProfileData?

    @Insert(entity = ProfileData::class)
    suspend fun createProfile(profile: ProfileData): Long

    @Delete(entity = ProfileData::class)
    suspend fun deleteProfile(id: DeleteProfileTuple)

    @Update
    suspend fun updateProfile(profileData: ProfileData)
}