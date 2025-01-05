package com.example.features.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesModel(
    @PrimaryKey(autoGenerate = true) val noteId: Int = 0,
    val title: String,
    val description: String,
    val userId: Int
)
