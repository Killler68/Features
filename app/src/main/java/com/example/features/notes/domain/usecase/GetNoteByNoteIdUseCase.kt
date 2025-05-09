package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository

class GetNoteByNoteIdUseCase(
    private val notesTaskRepository: NotesTaskRepository
) {

    suspend operator fun invoke(userId: Int, noteId: Int) =
        notesTaskRepository.getNoteByNoteId(userId, noteId)
}