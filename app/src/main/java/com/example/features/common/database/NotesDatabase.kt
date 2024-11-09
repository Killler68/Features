package com.example.features.common.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Notes::class]
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
}