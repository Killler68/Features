package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel

interface NotesRepository {

    fun getNotes(): List<NotesModel>
    fun addNote(note: NotesModel)
    fun deleteNote(note: NotesModel)
    fun updateNote(id: Int, note: NotesModel)
}