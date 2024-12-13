package com.example.features.notes.usecase

import com.example.features.notes.model.NotesModel

interface NotesRepository {

    suspend fun getNotes(userId: Int): List<NotesModel>
    suspend fun getNoteById(noteId: Int): NotesModel?
//    suspend fun getNotesByUser(userId: Int): List<NotesModel>
//    suspend fun addNoteForUser(note: Notes)
    suspend fun addNote(note: NotesModel)
    suspend fun deleteNote(note: NotesModel)
    suspend fun updateNote(note: NotesModel)
}