package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository
import com.example.features.notes.domain.entities.NoteTaskItem

class UpdateNotesTaskUseCase(
    private val repository: NotesTaskRepository
) {

    suspend operator fun invoke(task: NoteTaskItem) = repository.updateNotesTask(task)
}