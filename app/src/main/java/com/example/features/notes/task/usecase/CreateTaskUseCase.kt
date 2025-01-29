package com.example.features.notes.task.usecase

import com.example.features.common.repository.task.TaskRepository
import com.example.features.notes.task.model.TaskModel

class CreateTaskUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(task: TaskModel) = repository.createTask(task)
}