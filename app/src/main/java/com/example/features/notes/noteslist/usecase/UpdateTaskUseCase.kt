package com.example.features.notes.noteslist.usecase

import com.example.features.common.repository.task.TaskRepository
import com.example.features.notes.task.model.TaskModel

class UpdateTaskUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(task: TaskModel) = repository.updateTask(task)
}