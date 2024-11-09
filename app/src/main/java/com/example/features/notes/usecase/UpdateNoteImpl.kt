package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.UpdateNote

class UpdateNoteImpl(
    private val repository: NotesRepository
) : UpdateNote {

    override suspend fun invoke(note: NotesModel) = repository.updateNote(note)
}