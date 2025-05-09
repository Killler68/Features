package com.example.features.notes.presentation.models

sealed class NotesTaskSideEffect {
    data class NavigateTo(val route: String) : NotesTaskSideEffect()
}