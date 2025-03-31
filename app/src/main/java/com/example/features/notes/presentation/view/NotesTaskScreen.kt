package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.models.NotesTaskSideEffect
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun NotesTaskScreen(userId: Int, navController: NavController) {

    val viewModel: NotesTaskViewModel = getViewModel()
    var isDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is NotesTaskSideEffect.NavigateTo -> navController.navigate(effect.route)
                NotesTaskSideEffect.None -> isDialogVisible = false
                NotesTaskSideEffect.Popup -> isDialogVisible = true
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.dispatch(NotesTaskEvent.LoadNotes(userId))
        viewModel.dispatch(NotesTaskEvent.LoadTask(userId))
    }

    Scaffold(
        topBar = { NotesTaskTopBar(userId, viewModel) },
        content = { NotesTaskContent(it, isDialogVisible, userId) }
    )
}


@Composable
fun NotesTaskContent(
    paddingValues: PaddingValues,
    isVisibleDialog: Boolean,
    userId: Int,
) {
    val viewModel: NotesTaskViewModel = getViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        NotesTaskCombinedList(userId)
        NotesTaskChoiceDialog(isVisibleDialog, userId)
        NotesTaskAddImage(viewModel)
    }
}