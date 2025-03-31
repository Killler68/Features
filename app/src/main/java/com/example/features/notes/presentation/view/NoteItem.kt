package com.example.features.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel

@Composable
fun NoteItem(noteItem: NoteTaskItem, userId: Int, onClick: () -> Unit) {
    val viewModel: NotesTaskViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    var editTitle by remember(state.editingNote?.id) { mutableStateOf(noteItem.title) }
    var editDescription by remember(state.editingNote?.id) { mutableStateOf(noteItem.description) }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(LightGray)
            .clickable { onClick() }
    ) {
        noteItem.description?.let {
            Text(
                it,
                modifier = Modifier.padding(10.dp),
                maxLines = 8,
                style = TextStyle(fontSize = 12.sp)
            )
        }

        NoteItemImages(viewModel, userId, noteItem)
        NoteItemEditingDialog(
            state = state,
            noteItem = noteItem,
            userId = userId,
            editTitle = editTitle,
            editDescription = editDescription,
            onValueTitle = { editTitle = it },
            onValueDescription = { editDescription = it }
        )
    }
}