package com.example.features.notes.domain.usecase

import com.example.features.notes.data.repository.NotesTaskRepository
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.domain.entities.TypeItem

class AddNoteUseCase(
    private val repository: NotesTaskRepository
) {

    suspend operator fun invoke(note: NoteTaskItem) {
        val dateNote = System.currentTimeMillis() / 1000

        repository.createNotesTask(
            NoteTaskItem(
                note.id,
                type = TypeItem.NOTE,
                note.title,
                note.description,
                note.userId,
                note.isComplete,
                dateNote,
                note.backgroundColor
            )
        )
    }
}