package com.example.features.notes.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.features.R


@Composable
fun TaskItemSwipeImage(
    offsetX: Float,
    halfSwipe: Float,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    if (offsetX < halfSwipe) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(R.drawable.pencil),
                contentDescription = stringResource(R.string.editing_note_image_description),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(26.dp)
                    .clickable { onEditClick() }
            )
            Image(
                painter = painterResource(R.drawable.trash_bucket),
                contentDescription = stringResource(R.string.delete_note_image_description),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(26.dp)
                    .clickable { onDeleteClick() }
            )
        }
    }
}