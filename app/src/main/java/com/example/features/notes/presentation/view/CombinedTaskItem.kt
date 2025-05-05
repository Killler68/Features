package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CombinedTaskItem(userId: Int) {
    val viewModel: NotesTaskViewModel = getViewModel()
    val taskState = viewModel.state.collectAsState()
    val tasks = taskState.value.tasks
    Column {
        tasks.forEach { taskItem ->
            TaskItem(
                userId = userId,
                taskItem = taskItem,
                onCheckedChange = { isChecked ->
                    viewModel.dispatch(
                        NotesTaskEvent.OnClickUpdateNote(
                            taskItem.userId,
                            taskItem.copy(isComplete = isChecked)
                        )
                    )
                },
            )
        }
    }
}