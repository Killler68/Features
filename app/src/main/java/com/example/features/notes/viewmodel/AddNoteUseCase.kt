package com.example.features.notes.viewmodel

import com.example.features.notes.model.NotesModel

interface AddNoteUseCase {

    suspend operator fun invoke(note: NotesModel)
}