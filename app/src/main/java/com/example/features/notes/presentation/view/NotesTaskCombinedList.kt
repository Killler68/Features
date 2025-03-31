package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun NotesTaskCombinedList(userId: Int) {
    val viewModel: NotesTaskViewModel = getViewModel()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { CombinedTaskItem(viewModel) }
        item { CombinedNoteItem(userId) }
    }
}