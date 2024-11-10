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
                    modifier = Modifier.weight(0.5f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "image",
                        modifier = Modifier
                            .clickable { viewModel.removeNote(notesModel) }
                            .size(48.dp),
                        Alignment.CenterStart
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "image",
                        modifier = Modifier
                            .clickable { viewModel.isEditing.value = true }
                            .size(48.dp),
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
    }

    if (viewModel.isEditing.value) {
        DsNote(
            title = editTitle,
            description = editDescription,
            onTitleChange = { editTitle = it },
            onDescriptionChange = { editDescription = it },
            onDismiss = { viewModel.isEditing.value = false },
            onSave = {
                viewModel.updateNote(
                    notesModel.copy(
                        title = editTitle,
                        description = editDescription
                    )
                )
                viewModel.isEditing.value = false
            }
        )
    }
}