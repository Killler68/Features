package com.example.features.notes.noteslist.usecase

import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.noteslist.viewmodel.GetNotesUseCase

class GetNotesUseCaseImpl(
    private val repository: NotesRepository
) : GetNotesUseCase {

    override suspend fun invoke(userId: Int): List<NotesModel> = repository.getNotes(userId)
}