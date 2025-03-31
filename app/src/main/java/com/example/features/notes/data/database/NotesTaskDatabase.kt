package com.example.features.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [NoteTask::class]
)
abstract class NotesTaskDatabase : RoomDatabase() {

    abstract fun taskDao(): NotesTaskDao
}