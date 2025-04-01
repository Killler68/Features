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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.presentation.models.NotesTaskState
import com.example.features.ui.theme.Cyan
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TaskItem(
    userId: Int,
    task: NoteTaskItem,
    state: NotesTaskState,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    var editTitle by remember(state.editingNote?.id) { mutableStateOf(task.title) }

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
            }
    ) {
        TaskItemSwipeImage(offsetX, halfSwipe,
            onDeleteClick = {
                onDeleteClick()
                coroutineScope.launch { animateOffsetToZero { offsetX = 0f } }
            },
            onEditClick = {
                onEditClick()
                coroutineScope.launch { animateOffsetToZero { offsetX = 0f } }
            }
        )
        TaskItemContent(offsetX, task, onCheckedChange)
        TaskItemEditingDialog(
            state = state,
            taskItem = task,
            userId = userId,
            editTitle = editTitle,
            onValueTitle = { editTitle = it },
        )
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
            .background(Color.LightGray),
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
            Text(text = it, fontSize = 12.sp, modifier = Modifier.padding(5.dp))
        }
    }
}