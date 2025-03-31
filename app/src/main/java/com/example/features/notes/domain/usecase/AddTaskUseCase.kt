package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.domain.entities.TypeItem

class AddTaskUseCase(
    private val repository: NotesTaskRepository
) {

    suspend operator fun invoke(task: NoteTaskItem) = repository.createNotesTask(
        NoteTaskItem(
            task.id,
            type = TypeItem.TASK,
            task.title,
            task.description,
            task.userId,
            task.isComplete
        )
    )
}