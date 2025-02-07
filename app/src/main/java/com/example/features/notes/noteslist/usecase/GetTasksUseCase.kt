package com.example.features.notes.noteslist.usecase

import com.example.features.common.repository.task.TaskRepository

class GetTasksUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(userId: Int) = repository.getTasks(userId)
}