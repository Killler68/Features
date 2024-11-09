package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.GetNotesUseCase

class GetNotesUseCaseImpl(
    private val repository: NotesRepository
) : GetNotesUseCase {

    override suspend fun invoke(): List<NotesModel> = repository.getNotes()
}