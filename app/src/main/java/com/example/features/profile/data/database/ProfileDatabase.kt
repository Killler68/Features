package com.example.features.profile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.features.profile.data.database.model.ProfileData


@Database(
    version = 1,
    entities = [ProfileData::class]
)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}