package com.example.features.profile.screen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.profile.model.ProfileEvent
import com.example.features.profile.viewmodel.ProfileViewModel
import com.example.features.ui.theme.Cyan


@Composable
fun BottomEditingButtons(viewModel: ProfileViewModel) {

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
    ) {
        Row {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .padding(start = 10.dp, end = 5.dp),
                onClick = {
                    viewModel.dispatch(
                        ProfileEvent.OnClickCancel
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Отменить",
                        fontSize = 14.sp
                    )
                }

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .padding(start = 10.dp, end = 5.dp),
                onClick = {
                    viewModel.dispatch(
                        ProfileEvent.OnClickApply
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Подтвердить",
                        fontSize = 14.sp
                    )
                }

            }
        }
    }
}