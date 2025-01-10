package com.example.features.notes.noteslist.viewmodel

import com.example.features.notes.common.model.NotesModel

interface UpdateNote {

    suspend operator fun invoke(note: NotesModel)
}