package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository
import com.example.features.notes.domain.entities.NoteTaskItem

class GetNotesUseCase(
    private val repository: NotesTaskRepository
) {

    suspend operator fun invoke(userId: Int): List<NoteTaskItem> = repository.getNotes(userId)
}