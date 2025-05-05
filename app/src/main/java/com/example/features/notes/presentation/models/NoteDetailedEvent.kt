package com.example.features.notes.presentation.models

import com.example.features.notes.domain.entities.NoteTaskItem

sealed class NoteDetailedEvent {

    data class OnClickBack(val userId: Int) : NoteDetailedEvent()
    data class OnClickEditing(val note: NoteTaskItem) : NoteDetailedEvent()
    data class OnClickDelete(val userId: Int, val note: NoteTaskItem?) : NoteDetailedEvent()
    data class LoadNoteDetailed(val userId: Int, val noteId: Int) : NoteDetailedEvent()
    data object OnClickCloseDialog : NoteDetailedEvent()
    data object OnClickShowDialog : NoteDetailedEvent()
}