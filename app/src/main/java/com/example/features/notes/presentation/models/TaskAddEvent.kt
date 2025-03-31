package com.example.features.notes.presentation.models

sealed class TaskAddEvent {

    data class OnClickBack(val userId: Int) : TaskAddEvent()
    data class CreateTask(val userId: Int, val title: String) : TaskAddEvent()
}