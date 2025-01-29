package com.example.features.notes.noteslist.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.navigation.Screens
import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.noteslist.viewmodel.ItemType
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun CombinedNoteItem(navController: NavController) {

    val viewModel: NotesViewModel = getViewModel()
    val noteList = viewModel.combinedList.filter { it.type == ItemType.NOTE }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (noteList.isNotEmpty()) Text(text = "Заметки", fontSize = 20.sp)

        noteList.chunked(2).forEach { rowNotes ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                rowNotes.forEach { noteItem ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        noteItem.title?.let {
                            NotesModel(
                                noteId = noteItem.id,
                                title = it,
                                description = noteItem.description ?: "",
                                userId = 0
                            )
                        }?.let {
                            NoteItem(
                                notesModel = it,
                                onClick = {
                                    navController.navigate(
                                        Screens.NotesDetail.createRouter(
                                            noteId = noteItem.id
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}