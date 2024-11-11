package com.example.features.common.database.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.features.common.database.user.model.UserData

@Database(
    version = 1,
    entities = [UserData::class]
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}