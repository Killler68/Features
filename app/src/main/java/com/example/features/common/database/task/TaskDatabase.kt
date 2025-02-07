package com.example.features.common.database.task

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Task::class]
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}