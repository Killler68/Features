package com.example.features.notes.common.usecase

import com.example.features.notes.common.model.NotesModel

interface NotesRepository {

    suspend fun getNotes(userId: Int): List<NotesModel>
    suspend fun getNoteById(noteId: Int): NotesModel?
    suspend fun addNote(note: NotesModel)
    suspend fun deleteNote(note: NotesModel)
    suspend fun updateNote(note: NotesModel)
}