package com.example.features.notes.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import com.example.features.ui.theme.Cyan


@Composable
fun NotesTaskAddImage(viewModel: NotesTaskViewModel) {

    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    )
    {
        Image(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_note_image_description),
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .padding(bottom = 20.dp, end = 40.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(Cyan)
                .padding(7.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { viewModel.dispatch(NotesTaskEvent.OnClickDialog) }
                )
        )
    }
}