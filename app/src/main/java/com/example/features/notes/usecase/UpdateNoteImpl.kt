package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.UpdateNote

class UpdateNoteImpl(
    private val repository: NotesRepository
) : UpdateNote {
    override fun invoke(id: Int, note: NotesModel) = repository.updateNote(id, note)
}