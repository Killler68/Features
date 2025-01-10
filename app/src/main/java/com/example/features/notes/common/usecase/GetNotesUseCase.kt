package com.example.features.notes.common.usecase

import com.example.features.notes.common.model.NotesModel

class GetNotesUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(userId: Int): List<NotesModel> = repository.getNotes(userId)
}