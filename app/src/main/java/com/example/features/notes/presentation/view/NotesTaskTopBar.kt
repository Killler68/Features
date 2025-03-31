package com.example.features.notes.presentation.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTaskTopBar(userId: Int, viewModel: NotesTaskViewModel) {
    TopAppBar(
        title = {
            TopBarScreen(
                imageOnBack = R.drawable.back,
                imageDescriptionOnBack = stringResource(R.string.back_image_description),
                { viewModel.dispatch(NotesTaskEvent.OnClickBack(userId)) },
                stringResource(R.string.notes_title)
            )
        }
    )
}