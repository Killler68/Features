package com.example.features.notes.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel


@Composable
fun NoteItemImages(viewModel: NotesTaskViewModel, userId: Int, noteItem: NoteTaskItem) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.trash_bucket),
            contentDescription = stringResource(R.string.delete_note_image_description),
            modifier = Modifier
                .clickable {
                    viewModel.dispatch(NotesTaskEvent.OnClickDeleteNote(userId, noteItem))
                }
                .size(20.dp),
            Alignment.CenterStart
        )
        Box(modifier = Modifier.weight(0.5f))
        Image(
            painter = painterResource(R.drawable.pencil),
            contentDescription = stringResource(R.string.update_note_image_description),
            modifier = Modifier
                .clickable {
                    viewModel.dispatch(NotesTaskEvent.OnClickEditNotesTask(noteItem))
                }
                .size(20.dp),
            Alignment.CenterEnd
        )
    }
}