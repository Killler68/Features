package com.example.features.notes.presentation.view

import androidx.compose.animation.animateContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.features.R
import com.example.features.ui.theme.Cyan

@Composable
fun NotesTaskConfirmationDialog(
    onDismiss: () -> Unit,
    onRemove: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.animateContentSize(),
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.is_remove)) },
        confirmButton = {
            Button(
                onClick = onRemove,
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text(stringResource(R.string.delete))
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text(stringResource(R.string.cancellation))
            }
        }
    )
}