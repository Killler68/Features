package com.example.features.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.notes.presentation.models.TaskAddSideEffect
import com.example.features.notes.presentation.viewmodel.TaskAddViewModel
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskAddScreen(userId: Int, navController: NavController) {

    val viewModel: TaskAddViewModel = getViewModel()
    var editTask by remember { mutableStateOf(viewModel.editingTask.value?.title ?: "") }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TaskAddSideEffect.NavigateTo -> navController.navigate(effect.router)
            }
        }
    }

    Scaffold(
        topBar = { TaskAddTopBar(userId, editTask) },
        content = {
            TaskAddContent(
                it,
                editTask,
                editTask.length
            ) { text -> editTask = text }
        }
    )
}


@Composable
fun TaskAddContent(
    paddingValues: PaddingValues,
    editTask: String,
    charCount: Int,
    onValueTask: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(LightGray)
    ) {
        TaskAddTextField(
            editTask = editTask,
            onValueTask = { newText ->
                if (newText.length <= 100) {
                    onValueTask(newText)
                }
            }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.max_char, charCount),
            fontSize = 12.sp,
            color = Cyan,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 5.dp)
        )
    }
}

@Composable
fun TaskAddTextField(
    editTask: String,
    onValueTask: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = editTask,
            onValueChange = onValueTask,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGray,
                unfocusedContainerColor = LightGray,
                unfocusedIndicatorColor = LightGray,
                focusedIndicatorColor = LightGray
            ),
            label = { Text(text = stringResource(R.string.edit_task)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp)
        )
    }
}