package com.example.features.notes.presentation.models

sealed class NoteAddSideEffect {

    data class NavigateTo(val route: String) : NoteAddSideEffect()
}