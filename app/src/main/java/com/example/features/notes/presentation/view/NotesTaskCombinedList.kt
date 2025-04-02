package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun NotesTaskCombinedList(userId: Int) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { CombinedTaskItem(userId) }
        item { CombinedNoteItem(userId) }
    }
}