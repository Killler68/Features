package com.example.features.notes.presentation.models

import com.example.features.notes.domain.entities.NoteTaskItem

sealed class NotesTaskEvent {
    data class LoadNotes(val userId: Int) : NotesTaskEvent()
    data class LoadTask(val userId: Int) : NotesTaskEvent()
    data class OnClickAddNote(val userId: Int) : NotesTaskEvent()
    data object OnClickDialog : NotesTaskEvent()
    data class OnClickBack(val userId: Int) : NotesTaskEvent()
    data class OnClickDeleteNote(val userId: Int, val note: NoteTaskItem) : NotesTaskEvent()
    data class OnClickUpdateNote(val userId: Int, val note: NoteTaskItem) : NotesTaskEvent()
    data class OnClickAddTask(val userId: Int) : NotesTaskEvent()
    data class OnClickNoteDetailed(val userId: Int, val noteId: Int) : NotesTaskEvent()
    data class OnClickEditNote(val note: NoteTaskItem?) : NotesTaskEvent()
    data object OnCloseDialog : NotesTaskEvent()
}