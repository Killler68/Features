package com.example.features.notes.task.screen

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.common.navigation.Screens
import com.example.features.notes.task.model.TaskModel
import com.example.features.notes.task.viewmodel.TaskViewModel
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavController) {

    val viewModel: TaskViewModel = getViewModel()

    val sharedViewModel: SharedViewModel = getViewModel()
    val currentUser by sharedViewModel.currentUser.collectAsState()
    val userId = currentUser?.id

    var editTask by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.back),
                                contentDescription = "back",
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .size(32.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(LightGray)
                                    .padding(7.dp)
                                    .clickable { navController.navigate(Screens.NotesList.route) }
                            )
                            Text(
                                text = "Добавить Задачу",
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
                                    contentDescription = "save_task",
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(LightGray)
                                        .padding(7.dp)
                                        .clickable {

                                            if (userId != null) {
                                                viewModel.createTask(
                                                    TaskModel(
                                                        taskText = editTask,
                                                        isComplete = false,
                                                        userId = userId,
                                                    )
                                                )
                                                navController.popBackStack()
                                            }
                                        }
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 10.dp, end = 15.dp)
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .size(height = 1.dp, width = 1.dp)
                        )
                    }
                },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                ) {
                    Image(
                        painter = painterResource(R.drawable.pencil),
                        contentDescription = "change_color_background",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 5.dp, top = 20.dp, bottom = 20.dp)
                            .size(24.dp)
                    )

                    TextField(
                        value = editTask,
                        onValueChange = { editTask = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = LightGray,
                            unfocusedContainerColor = LightGray,
                            unfocusedIndicatorColor = LightGray,
                            focusedIndicatorColor = LightGray
                        ),
                        label = { Text(text = "Введите задачу") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp)
                    )
                }
            }
        }
    )
}