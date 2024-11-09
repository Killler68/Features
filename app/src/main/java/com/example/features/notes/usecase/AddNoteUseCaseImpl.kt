package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.AddNoteUseCase

class AddNoteUseCaseImpl(
    private val repository: NotesRepository
) : AddNoteUseCase {

    override suspend fun invoke(note: NotesModel) = repository.addNote(note)
}