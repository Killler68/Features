package com.example.features.notes.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.navigation.Screens
import com.example.features.notes.design.DsNote
import com.example.features.notes.viewmodel.NotesViewModel
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel

@Composable
fun DsNoteDetail(noteId: Int, navController: NavController) {

    val viewModel: NotesViewModel = getViewModel()
    val note = viewModel.getNoteDetail(noteId)

    var lineTitle by remember { mutableIntStateOf(1) }

    note?.let {

        var editTitle by remember { mutableStateOf(it.title) }
        var editDescription by remember { mutableStateOf(it.description) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                        .padding(7.dp)
                        .clickable { navController.navigate(Screens.NotesList.route) }
                )
                Text(
                    text = it.title,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(0.5f)
                        .clickable { lineTitle = if (lineTitle == 1) 3 else 1 },
                    maxLines = lineTitle,
                )
                Image(
                    painter = painterResource(R.drawable.pencil),
                    contentDescription = "update_note",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                        .padding(7.dp)
                        .clickable {
                            viewModel.editingNoteId.value = it.id
                            editTitle = it.title
                            editDescription = it.description

                        }
                )
                Image(
                    painter = painterResource(R.drawable.trash_bucket),
                    contentDescription = "remove_note",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                        .padding(7.dp)
                        .clickable {
                            viewModel.removeNote(it)
                            navController.navigate(Screens.NotesList.route)
                        }
                )
            }
            if (viewModel.editingNoteId.value == it.id) {
                DsNote(
                    title = editTitle,
                    description = editDescription,
                    onTitleChange = { editTitle = it },
                    onDescriptionChange = { editDescription = it },
                    onDismiss = { viewModel.editingNoteId.value = null },
                    onSave = {
                        viewModel.updateNote(
                            it.copy(
                                title = editTitle,
                                description = editDescription
                            )
                        )
                        viewModel.editingNoteId.value = null
                    }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .size(height = 1.dp, width = 100.dp)
            )

            Text(
                it.description,
                style = TextStyle(
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(10.dp)

            )
        }
    } ?: Text("Заметка не найдена")
}