package com.example.features.common.database.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Int,
    val taskText: String,
    val isComplete: Boolean = false,
    val userId: Int
)
