package com.example.features.notes.data.repository

import com.example.features.notes.data.database.NoteTask
import com.example.features.notes.data.database.NotesTaskDao
import com.example.features.notes.domain.entities.NoteTaskItem

class NotesTaskRepositoryImpl(
    private val notesTaskDao: NotesTaskDao
) : NotesTaskRepository {

    override suspend fun getTasks(userId: Int): List<NoteTaskItem> =
        notesTaskDao.getTasks(userId).map {
            NoteTaskItem(
                it.id,
                it.type,
                it.title,
                it.description,
                it.userId,
                it.isComplete,
                it.createTime
            )
        }

    override suspend fun getNotes(userId: Int): List<NoteTaskItem> =
        notesTaskDao.getNotes(userId).map {
            NoteTaskItem(
                it.id,
                it.type,
                it.title,
                it.description,
                it.userId,
                it.isComplete,
                it.createTime
            )
        }

    override suspend fun getNoteByNoteId(userId: Int, noteId: Int): NoteTaskItem? {
        val note = notesTaskDao.getNoteByNoteId(userId, noteId)
        return note?.let {
            NoteTaskItem(
                id = it.id,
                type = it.type,
                title = it.title,
                description = it.description,
                userId = it.userId,
                isComplete = it.isComplete,
                createTime = it.createTime
            )
        }
    }

    override suspend fun createNotesTask(task: NoteTaskItem) {
        notesTaskDao.createNotesTask(
            NoteTask(
                id = 0,
                type = task.type,
                title = task.title,
                description = task.description,
                userId = task.userId,
                isComplete = task.isComplete,
                createTime = task.createTime
            )
        )
    }

    override suspend fun deleteNotesTask(task: NoteTaskItem) {
        notesTaskDao.deleteNotesTask(id = task.id)
    }

    override suspend fun updateNotesTask(task: NoteTaskItem) {
        notesTaskDao.updateNotesTask(
            NoteTask(
                id = task.id,
                type = task.type,
                title = task.title,
                description = task.description,
                isComplete = task.isComplete,
                userId = task.userId,
                createTime = task.createTime
            )
        )
    }
}