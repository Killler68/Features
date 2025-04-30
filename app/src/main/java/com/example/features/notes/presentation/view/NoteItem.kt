package com.example.features.notes.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.common.extension.formatToDayMonthString
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.models.NotesTaskState
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    noteItem: NoteTaskItem,
    userId: Int,
    onClick: () -> Unit
) {
    val viewModel: NotesTaskViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    var isMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(noteItem.backgroundColor))
            .clickable { onClick() }
            .combinedClickable(
                onClick = { onClick() },
                onLongClick = { isMenuExpanded = true }
            )
    ) {
        noteItem.description?.let {
            Text(
                it,
                modifier = Modifier.padding(10.dp),
                maxLines = 8,
                style = TextStyle(fontSize = 12.sp)
            )
            Text(
                text = noteItem.createTime.formatToDayMonthString(),
                fontSize = 8.sp,
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 5.dp)
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth())

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.select_background)) },
                onClick = {
                    isMenuExpanded = false
                    viewModel.dispatch(NotesTaskEvent.OnSelectTaskForColor(noteItem))
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.paint),
                        contentDescription = stringResource(R.string.select_background_image_description),
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.edit)) },
                onClick = {
                    isMenuExpanded = false
                    viewModel.dispatch(NotesTaskEvent.OnClickEditNotesTask(noteItem))
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.pencil),
                        contentDescription = stringResource(R.string.editing_note_image_description),
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.delete)) },
                onClick = {
                    isMenuExpanded = false
                    viewModel.dispatch(NotesTaskEvent.OnSelectNoteForDeletion(noteItem))
                    viewModel.dispatch(NotesTaskEvent.OnOpenConfirmationNoteDialog)
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.trash_bucket),
                        contentDescription = stringResource(R.string.delete_note_image_description),
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
        }
    }

    PopupDialogNoteItem(userId, state, noteItem, viewModel)
}

@Composable
fun PopupDialogNoteItem(
    userId: Int,
    state: NotesTaskState,
    noteItem: NoteTaskItem,
    viewModel: NotesTaskViewModel
) {

    var editTitle by remember(state.editingNote?.id) { mutableStateOf(noteItem.title) }
    var editDescription by remember(state.editingNote?.id) { mutableStateOf(noteItem.description) }

    if (state.isConfirmationDialogVisible) {
        NotesTaskConfirmationDialog(
            onDismiss = { viewModel.dispatch(NotesTaskEvent.OnCloseConfirmationNoteDialog) },
            onRemove = { viewModel.dispatch(NotesTaskEvent.OnClickDeleteNote(userId)) }
        )
    }

    if (state.isColorDialogVisible) {
        DialogColorSelection(
            state.availableColors,
            onDismiss = { viewModel.dispatch(NotesTaskEvent.OnCloseColorDialog) },
            onConfirm = { viewModel.dispatch(NotesTaskEvent.OnConfirmColor(it)) }
        )
    }

    NoteItemEditingDialog(
        state,
        noteItem,
        userId,
        editTitle,
        editDescription,
        { editTitle = it },
        { editDescription = it }
    )
}