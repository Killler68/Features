package com.example.features.notes.design

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.navigation.Screens
import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.NotesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DsNotesList(navController: NavController) {

    val viewModel: NotesViewModel = getViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsActionBar(navController)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .size(height = 1.dp, width = 100.dp)
        )
        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn {
                itemsIndexed(viewModel.stateGetNotes.value) { index, item ->
                    DsNotesListItems(index, item)
                }
            }

            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "image",
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 40.dp)
                    .size(54.dp)
                    .align(Alignment.BottomEnd)
                    .clickable { viewModel.isAddNote.value = true }
            )
        }
    }
}

@Composable
fun DsActionBar(navController: NavController) {

    var editTitle by remember { mutableStateOf("") }
    var editDescription by remember { mutableStateOf("") }

    val viewModel: NotesViewModel = getViewModel()

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "image",
            modifier = Modifier
                .size(50.dp)
                .clickable { navController.navigate(Screens.Features.route) }
        )

        if (viewModel.isAddNote.value) {
            DsNote(
                title = editTitle,
                description = editDescription,
                onTitleChange = { editTitle = it },
                onDescriptionChange = { editDescription = it },
                onDismiss = { viewModel.isAddNote.value = false },
                onSave = {
                    viewModel.createNote(NotesModel(editTitle, editDescription))
                    viewModel.isAddNote.value = false
                    editTitle = ""
                    editDescription = ""
                }
            )
        }

        Text(
            text = "Заметки",
            fontSize = 16.sp,
            modifier = Modifier
                .weight(0.7f)
                .padding(10.dp)
        )

        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "image",
            modifier = Modifier
                .size(50.dp)
        )
    }
}