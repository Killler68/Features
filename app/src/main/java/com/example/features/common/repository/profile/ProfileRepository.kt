package com.example.features.common.repository.profile

import com.example.features.common.database.profile.model.Profile

interface ProfileRepository {

    suspend fun getProfile(): List<Profile>
    suspend fun getProfileById(userId: Int): Profile?
    suspend fun addProfile(profile: Profile): Profile
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile(id: Int)
}