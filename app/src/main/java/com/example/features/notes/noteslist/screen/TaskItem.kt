package com.example.features.notes.noteslist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.navigation.Screens
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import com.example.features.notes.task.model.TaskModel
import org.koin.androidx.compose.getViewModel


@Composable
fun TaskItem(task: TaskModel, navController: NavController) {

    val checkedState = remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0.0f) }
    val scope = rememberCoroutineScope()

    var isActive = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .clickable { isActive.value = true }
    ) {


        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier
                .padding(10.dp)
        )

        Column {

            Text(
                text = "3 задачи",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )

            Text(
                text = "0/2",
                fontSize = 8.sp,
                modifier = Modifier
            )
        }

        LinearProgressIndicator(
            progress = progress,
            trackColor = Color.Red,
            modifier = Modifier
                .width(150.dp)
                .align(Alignment.CenterVertically)
                .padding(horizontal = 10.dp)
        )

        Image(
            painter = painterResource(R.drawable.note),
            contentDescription = "add_task",
            modifier = Modifier
                .padding(start = 20.dp)
                .size(36.dp)
                .fillMaxSize()
                .align(Alignment.CenterVertically)
        )


        if (isActive.value) {
            DialogTaskItem(
                onDismiss = {
                    isActive.value = false
                },
                task,
                navController
            )
        }
    }
}

@Composable
fun DialogTaskItem(
    onDismiss: () -> Unit,
    task: TaskModel,
    navController: NavController
) {

    val viewModel: NotesViewModel = getViewModel()

    val checkedState = remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0.0f) }

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                ) {

                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        modifier = Modifier
                            .padding(10.dp)
                    )

                    Column {

                        Text(
                            text = "3 задачи",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        )

                        Text(
                            text = "0/2",
                            fontSize = 8.sp,
                            modifier = Modifier
                        )
                    }

                    LinearProgressIndicator(
                        progress = progress,
                        trackColor = Color.Red,
                        modifier = Modifier
                            .width(120.dp)
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 10.dp)
                    )

                    Image(
                        painter = painterResource(R.drawable.note),
                        contentDescription = "add_task",
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .size(36.dp)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically)
                            .clickable { navController.navigate(Screens.TaskScreen.route) }
                    )

                }

                LazyColumn(

                ) {
                    itemsIndexed(
                        viewModel.getTask.value
                    ) { index, item ->
                        DialogItem(item)
                    }
                }
            }
        }
    }
}

@Composable
fun DialogItem(task: TaskModel) {

    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
        Text(
            text = task.taskText,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

