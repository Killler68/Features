package com.example.features.notes.task.model

data class TaskModel(
    val taskId: Int = 0,
    val taskText: String,
    val isComplete: Boolean,
    val userId: Int
)
