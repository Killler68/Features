package com.example.features.notes.noteslist.viewmodel

import com.example.features.notes.common.model.NotesModel

interface DeleteNote {

    suspend operator fun invoke(note: NotesModel)
}