package com.example.features.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.notes.presentation.models.NoteDetailedEvent
import com.example.features.notes.presentation.models.NoteDetailedSideEffect
import com.example.features.notes.presentation.viewmodel.NoteDetailedViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NoteDetailedScreen(userId: Int, noteId: Int, navController: NavController) {
    val viewModel: NoteDetailedViewModel = getViewModel()
    val note = viewModel.note.value
    var isDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is NoteDetailedSideEffect.NavigateTo -> navController.navigate(effect.route)
                NoteDetailedSideEffect.None -> isDialogVisible = false
                NoteDetailedSideEffect.Popup -> isDialogVisible = true
            }
        }
    }

    LaunchedEffect(note) {
        if (note == null) {
            viewModel.dispatch(NoteDetailedEvent.LoadNoteDetailed(userId, noteId))
        }
    }

    Scaffold(
        topBar = { NoteDetailedTopBar(userId = userId) },
        content = { paddingValues -> NoteDetailedContent(isDialogVisible, paddingValues) }
    )
}

@Composable
fun NoteDetailedContent(
    isVisibleDialog: Boolean,
    paddingValues: PaddingValues
) {
    val viewModel: NoteDetailedViewModel = getViewModel()
    val note by viewModel.note
    note?.let {
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            NoteDetailedEditingDialog(isVisibleDialog, viewModel)
            Text(
                it.description ?: "",
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}