package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel

@Composable
fun CombinedTaskItem(userId: Int, viewModel: NotesTaskViewModel) {
    val taskState by viewModel.state.collectAsState()

    Column {
        taskState.tasks.forEach { taskItem ->
            TaskItem(
                userId = userId,
                task = taskItem,
                state = taskState,
                onCheckedChange = { isChecked ->
                    viewModel.dispatch(
                        NotesTaskEvent.OnClickUpdateNote(
                            taskItem.userId,
                            taskItem.copy(isComplete = isChecked)
                        )
                    )
                },
                onDeleteClick = {
                    viewModel.dispatch(NotesTaskEvent.OnClickDeleteTask(userId, taskItem))
                },
                onEditClick = {
                    viewModel.dispatch(NotesTaskEvent.OnClickEditNotesTask(taskItem))
                }
            )
        }
    }
}