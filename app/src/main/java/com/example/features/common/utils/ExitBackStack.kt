package com.example.features.common.utils

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.features.ui.theme.Cyan
import kotlinx.coroutines.delay

@Composable
fun ExitBackStack() {
    var exit by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(exit) {
        if (exit) {
            delay(4000)
            exit = false
        }
    }

    BackHandler(enabled = true) {
        exit = true
    }

    if (exit) {
        AlertDialog(
            onDismissRequest = { exit = false },
            title = {
                Text(
                    text = "Выйти из приложения?",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        context.startActivity(Intent(Intent.ACTION_MAIN).apply {
                            addCategory(Intent.CATEGORY_HOME)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                        exit = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Cyan
                    )
                ) {
                    Text(
                        text = "Подтвердить",
                        fontSize = 12.sp
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { exit = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Cyan
                    )
                ) {
                    Text(
                        text = "Отмена",
                        fontSize = 12.sp
                    )
                }
            }
        )
    }
}