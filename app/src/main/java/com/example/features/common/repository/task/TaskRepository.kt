package com.example.features.common.repository.task

import com.example.features.notes.task.model.TaskModel

interface TaskRepository {


    suspend fun getTasks(userId: Int): List<TaskModel>
    suspend fun createTask(task: TaskModel)
    suspend fun deleteTask(task: TaskModel)
    suspend fun updateTask(task: TaskModel)
}