package com.example.features.notes.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray

@Composable
fun NoteEditingDialogView(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.edit_note)) },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(stringResource(R.string.title)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                TextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text(stringResource(R.string.description)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSave,
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text(stringResource(R.string.add))
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
@Composable
fun TaskEditingDialogView(
    title: String,
    onTitleChange: (String) -> Unit,
    charCount: Int,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.edit_note)) },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(stringResource(R.string.title)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = stringResource(R.string.max_char, charCount), fontSize = 12.sp, color = Cyan)
            }
        },
        confirmButton = {
            Button(
                onClick = onSave,
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text(stringResource(R.string.add))
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