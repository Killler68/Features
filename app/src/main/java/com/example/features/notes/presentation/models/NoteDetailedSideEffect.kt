package com.example.features.notes.presentation.models

sealed class NoteDetailedSideEffect {
    data class NavigateTo(val route: String) : NoteDetailedSideEffect()
    data object Popup : NoteDetailedSideEffect()
    data object None : NoteDetailedSideEffect()
}