package com.example.features.common.module

import androidx.room.Room
import com.example.features.common.database.user.UserDatabase
import org.koin.dsl.module

object DatabaseModule {
    val module = module {

        single {
            Room.databaseBuilder(
                get(),
                UserDatabase::class.java,
                "user_database"
            ).build()
        }
        single { get<UserDatabase>().userDao() }

    }
}