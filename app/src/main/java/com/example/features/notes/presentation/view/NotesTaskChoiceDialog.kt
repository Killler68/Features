package com.example.features.notes.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NotesTaskChoiceDialog(userId: Int) {
    val viewModel: NotesTaskViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    if (state.isChoiceDialogVisible) {
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