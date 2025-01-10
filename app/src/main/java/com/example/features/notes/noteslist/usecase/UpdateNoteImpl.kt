package com.example.features.notes.noteslist.usecase

import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.noteslist.viewmodel.UpdateNote

class UpdateNoteImpl(
    private val repository: NotesRepository
) : UpdateNote {

    override suspend fun invoke(note: NotesModel) = repository.updateNote(note)
}