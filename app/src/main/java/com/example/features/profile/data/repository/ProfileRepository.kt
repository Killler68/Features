package com.example.features.profile.data.repository

import com.example.features.profile.data.database.model.Profile

interface ProfileRepository {

    suspend fun getProfile(): List<Profile>
    suspend fun getProfileById(userId: Int): Profile?
    suspend fun addProfile(profile: Profile): Profile
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile(id: Int)
}