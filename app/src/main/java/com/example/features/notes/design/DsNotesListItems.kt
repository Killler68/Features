package com.example.features.notes.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.NotesViewModel
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel

@Composable
fun DsNoteGridItem(notesModel: NotesModel, onClick: () -> Unit) {

    val viewModel: NotesViewModel = getViewModel()

    var editTitle by remember { mutableStateOf(notesModel.title) }
    var editDescription by remember { mutableStateOf(notesModel.description) }

    Column(
        modifier = Modifier
            .clickable { onClick() }
    ) {

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .size(width = 100.dp, height = 160.dp)
        ) {
            Column {
                Text(
                    notesModel.description,
                    modifier = Modifier
                        .padding(10.dp),
                    maxLines = 5,
                    style = TextStyle(fontSize = 12.sp)
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.trash_bucket),
                        contentDescription = "remove_note",
                        modifier = Modifier
                            .clickable { viewModel.removeNote(notesModel) }
                            .size(24.dp)
                            .weight(0.5f),
                        Alignment.CenterStart
                    )

                    Image(
                        painter = painterResource(R.drawable.pencil),
                        contentDescription = "update_note",
                        modifier = Modifier
                            .clickable {
                                viewModel.editingNoteId.value = notesModel.noteId
                                editTitle = notesModel.title
                                editDescription = notesModel.description
                            }
                            .size(24.dp)
                            .weight(0.5f),
                        Alignment.CenterEnd

                    )
                }
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(width = 100.dp, height = 70.dp)
        ) {
            Text(
                notesModel.title,
                modifier = Modifier.padding(10.dp),
                maxLines = 2,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 14.sp)
            )
        }
        if (viewModel.editingNoteId.value == notesModel.noteId) {
            DsNote(
                title = editTitle,
                description = editDescription,
                onTitleChange = { editTitle = it },
                onDescriptionChange = { editDescription = it },
                onDismiss = { viewModel.editingNoteId.value = null },
                onSave = {
                    viewModel.updateNote(
                        notesModel.copy(
                            title = editTitle,
                            description = editDescription
                        )
                    )
                    viewModel.editingNoteId.value = null
                }
            )
        }
    }
}