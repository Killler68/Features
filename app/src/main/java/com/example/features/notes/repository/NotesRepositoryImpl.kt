package com.example.features.notes.repository

import com.example.features.notes.model.NotesModel
import com.example.features.notes.usecase.NotesRepository

class NotesRepositoryImpl : NotesRepository {

    private val notes: MutableList<NotesModel> = mutableListOf()

    override fun getNotes(): List<NotesModel> = notes

    override fun addNote(note: NotesModel) {
        notes.add(note)
    }

    override fun deleteNote(note: NotesModel) {
        notes.remove(note)
    }

    override fun updateNote(id: Int, note: NotesModel) {
        notes[id] = note
    }
}