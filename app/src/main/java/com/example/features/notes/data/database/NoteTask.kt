package com.example.features.notes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.features.notes.domain.entities.TypeItem

@Entity(tableName = "grid_table")
data class NoteTask(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: TypeItem,
    val title: String? = null,
    val description: String? = null,
    val userId: Int,
    val isComplete: Boolean = false,
    val createTime: Long,
    val backgroundColor: Int
)
