package com.example.features.notes.data.repository

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.features.notes.data.database.NoteTask
import com.example.features.notes.data.database.NotesTaskDao
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.models.ColorList

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
                it.createTime,
                it.backgroundColor
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
                it.createTime,
                it.backgroundColor
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
                createTime = it.createTime,
                backgroundColor = it.backgroundColor
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
                createTime = task.createTime,
                backgroundColor = task.backgroundColor
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
                createTime = task.createTime,
                backgroundColor = task.backgroundColor
            )
        )
    }

    override suspend fun getColors(): List<ColorList> = predefinedColors
}

private val predefinedColors = listOf(
    ColorList(1, "Красный", Color.Red.toArgb()),
    ColorList(2, "Зеленый", Color.Green.toArgb()),
    ColorList(3, "Синий", Color.Blue.toArgb()),
    ColorList(4, "Желтый", Color.Yellow.toArgb()),
    ColorList(5, "Черный", Color.Black.toArgb()),
    ColorList(6, "Белый", Color.White.toArgb()),
    ColorList(7, "Магента", 0xFFFF00FF.toInt()),
    ColorList(8, "Циан", 0xFF00FFFF.toInt()),
    ColorList(9, "Серый", Color.Gray.toArgb()),
)