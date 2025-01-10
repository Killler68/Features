package com.example.features.notes.noteadd.usecase

import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.noteadd.viewmodel.AddNoteUseCase
import com.example.features.notes.noteslist.usecase.NotesRepository

class AddNoteUseCaseImpl(
    private val repository: NotesRepository
) : AddNoteUseCase {

    override suspend fun invoke(note: NotesModel) = repository.addNote(note)
}