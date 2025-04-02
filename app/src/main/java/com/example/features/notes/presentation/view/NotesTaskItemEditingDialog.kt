package com.example.features.notes.presentation.view

import androidx.compose.runtime.Composable
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.models.NotesTaskState
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun NoteItemEditingDialog(
    state: NotesTaskState,
    noteItem: NoteTaskItem,
    userId: Int,
    editTitle: String?,
    editDescription: String?,
    onValueTitle: (String) -> Unit,
    onValueDescription: (String) -> Unit
) {
    val viewModel: NotesTaskViewModel = getViewModel()

    if (state.editingNote?.id == noteItem.id) {
        NoteEditingDialogView(
            title = editTitle ?: "",
            description = editDescription ?: "",
            onTitleChange = onValueTitle,
            onDescriptionChange = onValueDescription,
            onDismiss = { viewModel.dispatch(NotesTaskEvent.OnClickEditNotesTask(null)) },
            onSave = {
                viewModel.dispatch(
                    NotesTaskEvent.OnClickUpdateNote(
                        userId,
                        noteItem.copy(
                            title = editTitle,
                            description = editDescription
                        )
                    )
                )
            }
        )
    }
}

@Composable
fun TaskItemEditingDialog(
    state: NotesTaskState,
    taskItem: NoteTaskItem,
    userId: Int,
    editTitle: String?,
    onValueTitle: (String) -> Unit,
) {
    val viewModel: NotesTaskViewModel = getViewModel()

    if (state.editingNote?.id == taskItem.id) {
        TaskEditingDialogView(
            title = editTitle ?: "",
            onTitleChange = { newText ->
                if (newText.length <= 100) {
                    onValueTitle(newText)
                }
            },
            charCount = editTitle?.length ?: 0,
            onDismiss = { viewModel.dispatch(NotesTaskEvent.OnClickEditNotesTask(null)) },
            onSave = {
                viewModel.dispatch(
                    NotesTaskEvent.OnClickUpdateNote(userId, taskItem.copy(title = editTitle))
                )
            }
        )
    }
}