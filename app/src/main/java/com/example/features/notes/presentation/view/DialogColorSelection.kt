package com.example.features.notes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.notes.presentation.models.ColorList
import com.example.features.ui.theme.Cyan

@Composable
fun DialogColorSelection(
    colors: List<ColorList>,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var selected by remember { mutableStateOf<Int?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите цвет") },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.size(width = 250.dp, height = 130.dp)
            ) {
                items(colors) { color ->
                    val bord = if (selected == color.color) 2.dp else 0.dp
                    ColorItem(bord, color = color.color, onClick = { selected = color.color })
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { selected?.let(onConfirm) },
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text(
                    stringResource(R.string.confirm),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text(
                    stringResource(R.string.cancel),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    )
}

@Composable
fun ColorItem(bord: Dp, onClick: () -> Unit, color: Int) {
    Box(
        Modifier
            .size(36.dp)
            .border(bord, Color.Black, CircleShape)
            .clip(CircleShape)
            .background(Color(color))
            .clickable { onClick() }
    )
}