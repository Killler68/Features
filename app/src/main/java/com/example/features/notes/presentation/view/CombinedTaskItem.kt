package com.example.features.notes.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel


@Composable
fun CombinedTaskItem(viewModel: NotesTaskViewModel) {
    val task = viewModel.state.collectAsState()
    val taskList = task.value.tasks

    taskList.forEach { taskItem ->
        TaskItem(taskItem) { isChecked ->
            viewModel.dispatch(
                NotesTaskEvent.OnClickUpdateNote(
                    taskItem.userId,
                    taskItem.copy(userId = taskItem.userId, isComplete = isChecked)
                )
            )
        }
    }
}