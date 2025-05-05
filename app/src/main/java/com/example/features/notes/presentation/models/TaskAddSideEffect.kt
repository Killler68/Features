package com.example.features.notes.presentation.models

sealed class TaskAddSideEffect {

data class NavigateTo(val router: String) : TaskAddSideEffect()
}