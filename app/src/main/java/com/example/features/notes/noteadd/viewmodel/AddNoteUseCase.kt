package com.example.features.notes.noteadd.viewmodel

import com.example.features.notes.common.model.NotesModel

interface AddNoteUseCase {

    suspend operator fun invoke(note: NotesModel)
}