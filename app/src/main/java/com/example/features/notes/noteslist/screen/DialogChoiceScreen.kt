package com.example.features.notes.noteslist.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.features.common.navigation.Screens
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ChoiceScreen(navController: NavController) {

    val viewModel: NotesViewModel = getViewModel()

    if (viewModel.isChoiceNote.value) {
        DialogChoice(
            onDismiss = {
                viewModel.isChoiceNote.value = false
            },
            navigateToAddNote = {
                viewModel.isChoiceNote.value = false
                navController.navigate(Screens.NoteAddScreen.route)
            },
            navigateToTask = {
                viewModel.isChoiceNote.value = false
                navController.navigate(Screens.TaskScreen.route)
            }
        )
    }
}