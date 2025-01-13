package com.example.features.notes.common.usecase

import com.example.features.notes.common.model.NotesModel

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: NotesModel) = repository.deleteNote(note)
}