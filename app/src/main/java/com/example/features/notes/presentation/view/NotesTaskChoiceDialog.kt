package com.example.features.notes.presentation.view

import androidx.compose.runtime.Composable
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NotesTaskChoiceDialog(
    isVisibleDialog: Boolean,
    userId: Int
) {
    val viewModel: NotesTaskViewModel = getViewModel()

    if (isVisibleDialog) {
        NotesTaskChoiceDialogView(
            onDismiss = {
                viewModel.dispatch(NotesTaskEvent.OnCloseDialog)
            },
            navigateToAddNote = {
                viewModel.dispatch(NotesTaskEvent.OnClickAddNote(userId))
                viewModel.dispatch(NotesTaskEvent.OnCloseDialog)
            },
            navigateToTask = {
                viewModel.dispatch(NotesTaskEvent.OnClickAddTask(userId))
                viewModel.dispatch(NotesTaskEvent.OnCloseDialog)
            }
        )
    }
}