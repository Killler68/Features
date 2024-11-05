package com.example.features.notes.viewmodel

import com.example.features.notes.model.NotesModel

interface GetNoteByIdUseCase {

    operator fun invoke(noteId: Int): NotesModel?
}