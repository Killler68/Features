package com.example.features.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.notes.presentation.models.NoteAddSideEffect
import com.example.features.notes.presentation.viewmodel.NoteAddViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun NoteAddScreen(userId: Int, navController: NavController) {

    val viewModel: NoteAddViewModel = getViewModel()

    var editTitle by remember { mutableStateOf(viewModel.editingNote.value?.title ?: "") }
    var editDescription by remember {
        mutableStateOf(viewModel.editingNote.value?.description ?: "")
    }


    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is NoteAddSideEffect.NavigateTo -> navController.navigate(effect.route)
            }
        }
    }

    Scaffold(
        topBar = { NoteAddTopBar(userId, editTitle, editDescription) },
        content = {
            NoteAddContent(it,
                title = editTitle,
                description = editDescription,
                onTitleChange = { editTitle = it },
                onDescriptionChange = { editDescription = it }
            )
        }
    )
}

@Composable
fun NoteAddContent(
    paddingValues: PaddingValues,
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
            .background(Color.White)
    ) {

        TextField(
            value = title,
            onValueChange = onTitleChange,
            placeholder = { Text(stringResource(R.string.title), fontSize = 24.sp) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedIndicatorColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            textStyle = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .fillMaxWidth()
        )
        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = { Text(text = stringResource(R.string.description), fontSize = 18.sp) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedIndicatorColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}