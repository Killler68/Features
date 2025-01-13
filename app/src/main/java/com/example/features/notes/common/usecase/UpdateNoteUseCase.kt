package com.example.features.notes.common.usecase

import com.example.features.notes.common.model.NotesModel

class UpdateNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: NotesModel) = repository.updateNote(note)
}