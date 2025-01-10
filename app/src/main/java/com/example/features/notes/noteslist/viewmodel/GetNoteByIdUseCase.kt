package com.example.features.notes.noteslist.viewmodel

import com.example.features.notes.common.model.NotesModel

interface GetNoteByIdUseCase {

    suspend operator fun invoke(noteId: Int): NotesModel?
}