package com.example.features.notes.repository

import com.example.features.common.database.Notes
import com.example.features.common.database.NotesDao
import com.example.features.notes.model.NotesModel
import com.example.features.notes.usecase.NotesRepository

class NotesRepositoryImpl(
    private val notesDao: NotesDao
) : NotesRepository {

    override suspend fun getNotes(): List<NotesModel> = notesDao.getNotes().map {
        NotesModel(it.id, it.title, it.description)
    }

    override suspend fun getNoteById(noteId: Int): NotesModel? =
        notesDao.getNoteById(noteId)?.let {
            NotesModel(it.id, it.title, it.description)
        }

    override suspend fun addNote(note: NotesModel) {
        notesDao.addNote(Notes(note.id, note.title, note.description))
    }

    override suspend fun deleteNote(note: NotesModel) {
        notesDao.deleteNote(Notes(note.id, note.title, note.description))
    }
    override suspend fun updateNote(note: NotesModel) {
        notesDao.updateNote(Notes(id = note.id, title = note.title, description =  note.description))
    }
}