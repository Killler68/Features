package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.models.NotesTaskSideEffect
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun NotesTaskScreen(userId: Int, navController: NavController) {

    val viewModel: NotesTaskViewModel = getViewModel()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is NotesTaskSideEffect.NavigateTo -> navController.navigate(effect.route)
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.dispatch(NotesTaskEvent.LoadNotes(userId))
        viewModel.dispatch(NotesTaskEvent.LoadTask(userId))
    }

    Scaffold(
        topBar = { NotesTaskTopBar(userId, viewModel) },
        content = { NotesTaskContent(it, userId) }
    )
}


@Composable
fun NotesTaskContent(
    paddingValues: PaddingValues,
    userId: Int,
) {
    val viewModel: NotesTaskViewModel = getViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        NotesTaskCombinedList(userId)
        NotesTaskChoiceDialog(userId)
        NotesTaskAddImage(viewModel)
    }
}