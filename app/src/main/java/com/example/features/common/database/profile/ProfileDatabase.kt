package com.example.features.common.database.profile

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.features.common.database.profile.model.ProfileData


@Database(
    version = 1,
    entities = [ProfileData::class]
)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}