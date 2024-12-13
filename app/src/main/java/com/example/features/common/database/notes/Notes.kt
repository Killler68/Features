package com.example.features.common.database.notes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true) val noteId: Int = 0,
    val title: String,
    val description: String,
    val userId: Int
)
