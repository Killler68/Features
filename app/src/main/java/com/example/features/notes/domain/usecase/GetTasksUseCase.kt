package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository

class GetTasksUseCase(
    private val repository: NotesTaskRepository
) {

    suspend operator fun invoke(userId: Int) = repository.getTasks(userId)
}