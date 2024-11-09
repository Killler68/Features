package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel

interface NotesRepository {

    suspend fun getNotes(): List<NotesModel>
    suspend fun getNoteById(noteId: Int): NotesModel?
    suspend fun addNote(note: NotesModel)
    suspend fun deleteNote(note: NotesModel)
    suspend fun updateNote(note: NotesModel)
}