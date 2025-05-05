package com.example.features.notes.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.features.notes.presentation.models.NoteDetailedEvent
import com.example.features.notes.presentation.viewmodel.NoteDetailedViewModel


@Composable
fun NoteDetailedEditingDialog(
    isVisibleDialog: Boolean,
    viewModel: NoteDetailedViewModel
) {
    val note by viewModel.note

    note?.let { noteItem ->
        if (isVisibleDialog) {
            var editTitle by rememberSaveable { mutableStateOf(noteItem.title.orEmpty()) }
            var editDescription by rememberSaveable { mutableStateOf(noteItem.description.orEmpty()) }

            NoteEditingDialogView(
                title = editTitle,
                description = editDescription,
                onTitleChange = { editTitle = it },
                onDescriptionChange = { editDescription = it },
                onDismiss = { viewModel.dispatch(NoteDetailedEvent.OnClickCloseDialog) },
                onSave = {
                    viewModel.dispatch(
                        NoteDetailedEvent.OnClickEditing(
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
}