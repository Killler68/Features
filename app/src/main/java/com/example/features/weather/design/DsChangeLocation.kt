package com.example.features.weather.design

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray

@Composable
fun DsChangeLocation(
    title: String,
    onCityChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите город") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = onCityChange,
                    label = { Text("Выберите город") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSave,
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text("Добавить")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text("Отмена")
            }
        }
    )
}


