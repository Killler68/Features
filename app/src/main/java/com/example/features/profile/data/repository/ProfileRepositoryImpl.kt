package com.example.features.profile.data.repository

import com.example.features.profile.data.database.ProfileDao
import com.example.features.profile.data.database.model.Profile
import com.example.features.profile.data.database.model.ProfileData
import com.example.features.profile.data.database.model.toProfile
import com.example.features.profile.data.database.tuple.CreateProfileTuple
import com.example.features.profile.data.database.tuple.DeleteProfileTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(
    private val profileDao: ProfileDao
) : ProfileRepository {

    override suspend fun getProfile(): List<Profile> =
        withContext(Dispatchers.IO) {
            val profile = profileDao.getProfileDao()
            profile.map { it.toProfile() }.toList()
        }

    override suspend fun getProfileById(userId: Int): Profile? =
        withContext(Dispatchers.IO) {
            val profile =
                profileDao.getProfileByUserId(userId)?.toProfile()
            profile
        }

    override suspend fun addProfile(profile: Profile): Profile =
        withContext(Dispatchers.IO) {
            val profileId = profileDao.createProfile(
                CreateProfileTuple(
                    profile.id,
                    profile.email,
                    profile.name,
                    profile.age,
                    profile.city,
                    profile.nationality
                )
            )

            val createdProfile = profileDao.getProfileByUserId(profile.userId)?.toProfile()
            createdProfile
                ?: profile.copy(id = profileId.toInt())
        }

    override suspend fun updateProfile(profile: Profile) {
        profileDao.updateProfile(
            ProfileData(
                id = profile.id,
                userId = profile.userId,
                email = profile.email,
                name = profile.name,
                age = profile.age,
                city = profile.city,
                nationality = profile.nationality
            )
        )
    }

    override suspend fun deleteProfile(id: Int) =
        withContext(Dispatchers.IO) {
            profileDao.deleteProfile(
                DeleteProfileTuple(id)
            )
        }
}