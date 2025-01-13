package com.example.features.notes.noteadd.usecase

import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.common.usecase.NotesRepository

class AddNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: NotesModel) = repository.addNote(note)
}