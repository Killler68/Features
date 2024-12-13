package com.example.features.common.database.profile

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.features.common.database.profile.model.UserAdditionalInfoData


@Database(
    version = 1,
    entities = [UserAdditionalInfoData::class]
)
abstract class UserAdditionalInfoDatabase : RoomDatabase() {

    abstract fun userAdditionalInfoDao(): UserAdditionalInfoDao
}