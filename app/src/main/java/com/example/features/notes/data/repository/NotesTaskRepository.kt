package com.example.features.notes.data.repository

import com.example.features.notes.domain.entities.NoteTaskItem

interface NotesTaskRepository {


    suspend fun getTasks(userId: Int): List<NoteTaskItem>
    suspend fun getNotes(userId: Int): List<NoteTaskItem>
    suspend fun getNoteByNoteId(userId: Int, noteId: Int): NoteTaskItem?
    suspend fun createNotesTask(task: NoteTaskItem)
    suspend fun deleteNotesTask(task: NoteTaskItem)
    suspend fun updateNotesTask(task: NoteTaskItem)
}