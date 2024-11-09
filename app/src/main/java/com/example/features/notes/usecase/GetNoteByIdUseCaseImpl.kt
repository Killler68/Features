package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.GetNoteByIdUseCase

class GetNoteByIdUseCaseImpl(
    private val repository: NotesRepository
) : GetNoteByIdUseCase {

    override suspend fun invoke(noteId: Int): NotesModel? = repository.getNoteById(noteId)
}