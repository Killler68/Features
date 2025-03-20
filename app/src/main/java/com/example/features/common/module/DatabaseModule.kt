package com.example.features.common.module

import androidx.room.Room
import com.example.features.common.database.notes.NotesDatabase
import com.example.features.common.database.task.TaskDatabase
import com.example.features.common.database.user.UserDatabase
import org.koin.dsl.module

object DatabaseModule {
    val module = module {

        single {
            Room.databaseBuilder(
                get(),
                NotesDatabase::class.java,
                "notes_database"
            ).build()
        }
        single { get<NotesDatabase>().notesDao() }

        single {
            Room.databaseBuilder(
                get(),
                TaskDatabase::class.java,
                "task_database"
            ).build()
        }
        single { get<TaskDatabase>().taskDao() }

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