package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.DeleteNote

class DeleteNoteImpl(
    private val repository: NotesRepository
) : DeleteNote {

    override suspend
    fun invoke(note: NotesModel) = repository.deleteNote(note)
}