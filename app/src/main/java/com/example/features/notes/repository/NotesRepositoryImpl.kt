package com.example.features.notes.repository

import com.example.features.common.database.notes.Notes
import com.example.features.common.database.notes.NotesDao
import com.example.features.notes.model.NotesModel
import com.example.features.notes.usecase.NotesRepository

class NotesRepositoryImpl(
    private val notesDao: NotesDao
) : NotesRepository {

    override suspend fun getNotes(userId: Int): List<NotesModel> = notesDao.getNotes(userId).map {
        NotesModel(it.noteId, it.title, it.description, it.userId)
    }

    override suspend fun getNoteById(noteId: Int): NotesModel? =
        notesDao.getNoteById(noteId)?.let {
            NotesModel(it.noteId, it.title, it.description, it.userId)
        }

    override suspend fun addNote(note: NotesModel) {
        notesDao.addNote(Notes(0, note.title, note.description, note.userId))
    }

    override suspend fun deleteNote(note: NotesModel) {
        notesDao.deleteNote(noteId = note.noteId)
    }

    override suspend fun updateNote(note: NotesModel) {
        notesDao.updateNote(
            Notes(
                noteId = note.noteId,
                title = note.title,
                description = note.description,
                userId = note.userId
            )
        )
    }
}