package com.example.features.notes.viewmodel

import com.example.features.notes.model.NotesModel

interface UpdateNote {

    suspend operator fun invoke(note: NotesModel)
}