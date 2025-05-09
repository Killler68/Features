package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository
import com.example.features.notes.presentation.models.ColorList

class SelectColorBackgroundTaskUseCase(
    private val repository: NotesTaskRepository
) {
    suspend operator fun invoke(): List<ColorList> = repository.getColors()
}