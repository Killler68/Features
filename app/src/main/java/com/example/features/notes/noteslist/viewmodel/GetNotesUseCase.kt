package com.example.features.notes.noteslist.viewmodel

import com.example.features.notes.common.model.NotesModel

interface GetNotesUseCase {

    suspend operator fun invoke(userId: Int): List<NotesModel>
}