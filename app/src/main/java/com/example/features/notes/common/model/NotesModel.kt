package com.example.features.notes.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.features.notes.noteslist.viewmodel.ItemType

@Entity(tableName = "notes")
data class NotesModel(
    @PrimaryKey(autoGenerate = true) val noteId: Int = 0,
    val title: String,
    val description: String,
    val userId: Int
)
