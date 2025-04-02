package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun CombinedNoteItem(userId: Int) {

    val viewModel: NotesTaskViewModel = getViewModel()
    val note = viewModel.state.collectAsState()
    val noteList = note.value.notes

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        noteList.chunked(2).forEach { rowNotes ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                rowNotes.forEach { noteItem ->
                    Box(modifier = Modifier.weight(1f)) {
                        NoteItem(
                            noteItem = noteItem,
                            userId = userId,
                            onClick = {
                                viewModel.dispatch(
                                    NotesTaskEvent.OnClickNoteDetailed(userId, noteItem.id)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}