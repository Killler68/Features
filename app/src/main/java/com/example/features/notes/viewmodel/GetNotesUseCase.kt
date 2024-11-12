package com.example.features.notes.viewmodel

import com.example.features.notes.model.NotesModel

interface GetNotesUseCase {

    suspend operator fun invoke(userId: Int): List<NotesModel>
}