package com.example.features.weather.screen

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
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.model.WeatherState

@Composable
fun DsChangeLocation(
    title: String,
    onCityChange: (String) -> Unit,
    onDismiss: () -> Unit,
    state: WeatherState.Success,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Выберите город",
                color = weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.TEXT)
            )
        },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = onCityChange,
                    label = { Text("Выберите город") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = weatherColorExtension(
                            state.weatherWeek.first().partDay,
                            ColorCategory.CARD
                        ),
                        focusedContainerColor = weatherColorExtension(
                            state.weatherWeek.first().partDay,
                            ColorCategory.CARD
                        ),
                        focusedTextColor = weatherColorExtension(
                            state.weatherWeek.first().partDay,
                            ColorCategory.TEXT
                        ),
                        unfocusedTextColor = weatherColorExtension(
                            state.weatherWeek.first().partDay,
                            ColorCategory.TEXT
                        ),
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(12.dp)),
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSave,
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.CARD)
                )
            ) {
                Text(
                    "Добавить",
                    color = weatherColorExtension(
                        state.weatherWeek.first().partDay,
                        ColorCategory.TEXT
                    )
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    weatherColorExtension(state.weatherWeek.first().partDay, ColorCategory.CARD)
                )
            ) {
                Text(
                    "Отмена",
                    color = weatherColorExtension(
                        state.weatherWeek.first().partDay,
                        ColorCategory.TEXT
                    )
                )
            }
        },
        containerColor = weatherColorExtension(
            state.weatherWeek.first().partDay,
            ColorCategory.DIALOG
        )
    )
}


