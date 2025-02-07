package com.example.features.notes.noteslist.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.notes.noteslist.viewmodel.GridItem
import com.example.features.notes.task.model.TaskModel


@Composable
fun CombinedTaskItem(navController: NavController, item: GridItem) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .wrapContentHeight()
    ) {
        item.title?.let {
            TaskModel(
                taskId = item.id,
                taskText = it,
                isComplete = false,
                userId = 0
            )
        }?.let {
            TaskItem(
                task = it,
                navController
            )
        }
    }
}