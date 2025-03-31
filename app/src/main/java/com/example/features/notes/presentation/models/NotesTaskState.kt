package com.example.features.notes.presentation.models

import com.example.features.notes.domain.entities.NoteTaskItem

data class NotesTaskState(
    val notes: List<NoteTaskItem> = emptyList(),
    val tasks: List<NoteTaskItem> = emptyList(),
    val editingNote: NoteTaskItem? = null
)