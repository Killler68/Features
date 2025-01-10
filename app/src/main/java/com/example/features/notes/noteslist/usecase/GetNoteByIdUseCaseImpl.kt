package com.example.features.notes.noteslist.usecase

import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.noteslist.viewmodel.GetNoteByIdUseCase

class GetNoteByIdUseCaseImpl(
    private val repository: NotesRepository
) : GetNoteByIdUseCase {

    override suspend fun invoke(noteId: Int): NotesModel? = repository.getNoteById(noteId)
}