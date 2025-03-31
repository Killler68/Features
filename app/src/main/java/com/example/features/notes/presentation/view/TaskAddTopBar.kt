package com.example.features.notes.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.notes.presentation.models.TaskAddEvent
import com.example.features.notes.presentation.viewmodel.TaskAddViewModel
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskAddTopBar(userId: Int, editTask: String) {
    val viewModel: TaskAddViewModel = getViewModel()
    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TaskAddTopBarContent(userId, viewModel, editTask)
            }
        },
    )
}

@Composable
fun TaskAddTopBarContent(userId: Int, viewModel: TaskAddViewModel, editTask: String) {
    Row {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = stringResource(R.string.back_image_description),
            modifier = Modifier
                .padding(end = 10.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .padding(7.dp)
                .clickable { viewModel.dispatch(TaskAddEvent.OnClickBack(userId)) }
        )
        Text(
            text = stringResource(R.string.add_task),
            fontSize = 18.sp,
            color = Color.Black
        )

        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.note),
                contentDescription = stringResource(R.string.save_task_image_description),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightGray)
                    .padding(7.dp)
                    .clickable {
                        if (editTask.isNotEmpty())
                            viewModel.dispatch(TaskAddEvent.CreateTask(userId, editTask))
                    }
            )
        }
    }
}