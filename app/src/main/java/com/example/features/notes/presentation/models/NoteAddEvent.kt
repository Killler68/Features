package com.example.features.notes.presentation.models

sealed class NoteAddEvent {

    data class OnClickBack(val userId: Int) : NoteAddEvent()
    data class CreateNote(val userId: Int, val title: String, val description: String) : NoteAddEvent()
}