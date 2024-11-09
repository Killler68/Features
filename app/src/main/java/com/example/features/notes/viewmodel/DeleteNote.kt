package com.example.features.notes.viewmodel

import com.example.features.notes.model.NotesModel

interface DeleteNote {

    suspend operator fun invoke(note: NotesModel)
}