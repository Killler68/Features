package com.example.features.notes.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.models.NoteDetailedEvent
import com.example.features.notes.presentation.viewmodel.NoteDetailedViewModel
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailedTopBar(userId: Int) {
    val viewModel: NoteDetailedViewModel = getViewModel()

    val note = viewModel.note.value
    note?.let { noteItem ->
        TopAppBar(
            title = {
                Column { NoteDetailedTopBarContent(viewModel, userId, noteItem) }
            }
        )
    }
}

@Composable
fun NoteDetailedTopBarContent(
    viewModel: NoteDetailedViewModel,
    userId: Int,
    noteItem: NoteTaskItem
) {
    Row {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = stringResource(R.string.back_image_description),
            modifier = Modifier
                .padding(end = 10.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .padding(7.dp)
                .clickable { viewModel.dispatch(NoteDetailedEvent.OnClickBack(userId)) }
        )
        Text(
            text = noteItem.title ?: "",
            fontSize = 18.sp,
            color = Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(R.drawable.trash_bucket),
                contentDescription = stringResource(R.string.delete_note_image_description),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightGray)
                    .padding(7.dp)
                    .clickable {
                        viewModel.dispatch(
                            NoteDetailedEvent.OnClickDelete(
                                userId,
                                noteItem
                            )
                        )
                    }
            )
            Image(
                painter = painterResource(R.drawable.pencil),
                contentDescription = stringResource(R.string.editing_note_image_description),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightGray)
                    .padding(7.dp)
                    .clickable {
                        viewModel.dispatch(NoteDetailedEvent.OnClickShowDialog)
                    }
            )
        }
    }
}