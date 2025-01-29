package com.example.features.notes.noteslist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.notes.noteslist.viewmodel.ItemType
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun CombinedColumnList(navController: NavController) {

    val viewModel: NotesViewModel = getViewModel()

    val taskList = viewModel.combinedList.filter { it.type == ItemType.TASK }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (taskList.isNotEmpty()) {
            item {
                Text(
                    text = "Задачи",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )
            }
        }

        items(taskList) { item ->
            CombinedTaskItem(
                navController,
                item
            )
        }
        item { CombinedNoteItem(navController) }
    }
}

