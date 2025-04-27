package com.example.features.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.taskItemBackgroundColor
import com.example.features.common.extension.taskItemTextColor
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import com.example.features.ui.theme.Cyan
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun TaskItem(
    userId: Int,
    taskItem: NoteTaskItem,
    onCheckedChange: (Boolean) -> Unit
) {
    val viewModel: NotesTaskViewModel = getViewModel()
    val stateFlow = viewModel.state.collectAsState()
    val state = stateFlow.value

    var editTitle by remember(state.editingNote?.id) { mutableStateOf(taskItem.title) }

    var offsetX by remember { mutableFloatStateOf(0f) }
    val density = LocalDensity.current
    val maxSwipe = with(density) { -100.dp.toPx() }
    val halfSwipe = maxSwipe / 2

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        offsetX = if (offsetX < halfSwipe) maxSwipe else 0f
                    }
                ) { _, dragAmount ->
                    offsetX = (offsetX + dragAmount).coerceIn(maxSwipe, 0f)
                }
            },
        contentAlignment = Alignment.Center
    ) {

        TaskItemSwipeImage(
            offsetX, halfSwipe,
            onDeleteClick = {
                coroutineScope.launch { animateOffsetToZero { offsetX = 0f } }
                viewModel.dispatch(NotesTaskEvent.OnSelectNoteForDeletion(taskItem))
                viewModel.dispatch(NotesTaskEvent.OnOpenConfirmationTaskDialog)
            },
            onEditClick = {
                viewModel.dispatch(NotesTaskEvent.OnClickEditNotesTask(taskItem))
                coroutineScope.launch { animateOffsetToZero { offsetX = 0f } }
            }
        )

        if (state.isConfirmationTaskDialogVisible) {
            NotesTaskConfirmationDialog(
                onDismiss = {
                    viewModel.dispatch(NotesTaskEvent.OnCloseConfirmationTaskDialog)
                },
                onRemove = {
                    viewModel.dispatch(
                        NotesTaskEvent.OnClickDeleteTask(userId)
                    )
                }
            )

        }

        TaskItemContent(offsetX, taskItem, onCheckedChange)
        TaskItemEditingDialog(state, taskItem, userId, editTitle) { editTitle = it }
    }
}

suspend fun animateOffsetToZero(onAnimationEnd: () -> Unit) {
    var currentOffset = -100f
    while (currentOffset < 0f) {
        currentOffset += 10f
        delay(10)
    }
    onAnimationEnd()
}

@Composable
fun TaskItemContent(offsetX: Float, task: NoteTaskItem, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(taskItemBackgroundColor(isComplete = task.isComplete)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isComplete,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 5.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Cyan,
                uncheckedColor = Cyan
            )
        )
        task.title?.let {
            Text(
                text = it,
                fontSize = 12.sp,
                color = taskItemTextColor(isComplete = task.isComplete),
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}