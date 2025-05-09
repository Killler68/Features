package com.example.features.notes.presentation.models

import com.example.features.notes.domain.entities.NoteTaskItem

sealed class NotesTaskEvent {
    data class LoadNotes(val userId: Int) : NotesTaskEvent()
    data class LoadTask(val userId: Int) : NotesTaskEvent()
    data class OnClickAddNote(val userId: Int) : NotesTaskEvent()
    data object OnClickDialog : NotesTaskEvent()
    data class OnClickBack(val userId: Int) : NotesTaskEvent()
    data class OnClickDeleteNote(val userId: Int) : NotesTaskEvent()
    data class OnClickDeleteTask(val userId: Int) : NotesTaskEvent()
    data class OnClickUpdateNote(val userId: Int, val note: NoteTaskItem) : NotesTaskEvent()
    data class OnClickAddTask(val userId: Int) : NotesTaskEvent()
    data class OnClickNoteDetailed(val userId: Int, val noteId: Int) : NotesTaskEvent()
    data class OnClickEditNotesTask(val note: NoteTaskItem?) : NotesTaskEvent()
    data class OnSelectNoteForDeletion(val note: NoteTaskItem) : NotesTaskEvent()
    data object OnCloseDialog : NotesTaskEvent()
    data object OnOpenConfirmationTaskDialog : NotesTaskEvent()
    data object OnOpenConfirmationNoteDialog : NotesTaskEvent()
    data object OnCloseConfirmationTaskDialog : NotesTaskEvent()
    data object OnCloseConfirmationNoteDialog : NotesTaskEvent()
    data object OnOpenColorDialog : NotesTaskEvent()
    data object OnCloseColorDialog : NotesTaskEvent()
    data class OnSelectTaskForColor(val task: NoteTaskItem) : NotesTaskEvent()
    data class OnConfirmColor(val colorArgb: Int) : NotesTaskEvent()
}