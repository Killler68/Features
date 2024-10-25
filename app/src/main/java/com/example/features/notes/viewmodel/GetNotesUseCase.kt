package com.example.features.notes.viewmodel

import com.example.features.notes.model.NotesModel

interface GetNotesUseCase {

    operator fun invoke(): List<NotesModel>
}