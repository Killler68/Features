package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository
import com.example.features.notes.domain.entities.NoteTaskItem

class UpdateNoteUseCase(
    private val repository: NotesTaskRepository
) {

    suspend operator fun invoke(note: NoteTaskItem) = repository.updateNotesTask(note)
}