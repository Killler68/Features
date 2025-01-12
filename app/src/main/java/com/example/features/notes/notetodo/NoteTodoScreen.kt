package com.example.features.notes.notetodo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.navigation.Screens
import com.example.features.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTodoScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
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
                    .fillMaxSize()
                    .padding(it)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.pencil),
                        contentDescription = "add_todo",
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .size(24.dp)
                    )
                    Text(
                        "Добавление задачи...",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }


            }
        }
    )
}