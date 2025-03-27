package com.example.features.weather.presentation.view

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.presentation.models.WeatherState


@Composable
fun ChangeCityDialog(
    cityName: String,
    onCityChange: (String) -> Unit,
    onDismiss: () -> Unit,
    state: WeatherState.Success,
    onSave: () -> Unit
) {
    val partDay = state.weatherWeek.first().partDay
    val textColor = weatherColorExtension(partDay, ColorCategory.TEXT)
    val cardColor = weatherColorExtension(partDay, ColorCategory.CARD)
    val dialogColor = weatherColorExtension(partDay, ColorCategory.DIALOG)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.select_city), color = textColor) },
        text = {
            Column {
                TextField(
                    value = cityName,
                    onValueChange = onCityChange,
                    label = { Text(stringResource(R.string.select_city)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = cardColor,
                        focusedContainerColor = cardColor,
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        },
        confirmButton = {
            DialogButton(
                text = stringResource(R.string.add),
                onClick = onSave,
                color = cardColor,
                textColor = textColor
            )
        },
        dismissButton = {
            DialogButton(
                text = stringResource(R.string.cancel),
                onClick = onDismiss,
                color = cardColor,
                textColor = textColor
            )
        },
        containerColor = dialogColor
    )
}

@Composable
fun DialogButton(text: String, onClick: () -> Unit, color: Color, textColor: Color) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text, color = textColor)
    }
}


