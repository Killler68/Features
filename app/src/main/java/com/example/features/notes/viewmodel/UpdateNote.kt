package com.example.features.notes.viewmodel

import com.example.features.notes.model.NotesModel

interface UpdateNote {

    operator fun invoke(id: Int, note: NotesModel)
}