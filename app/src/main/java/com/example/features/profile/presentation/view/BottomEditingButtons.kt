package com.example.features.profile.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.profile.presentation.ProfileViewModel
import com.example.features.profile.presentation.models.ProfileEvent
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
            EditingButton(
                text = stringResource(R.string.cancel),
                onClick = { viewModel.dispatch(ProfileEvent.OnClickCancel) }
            )
            EditingButton(
                text = stringResource(R.string.confirm),
                onClick = { viewModel.dispatch(ProfileEvent.OnClickApply) }
            )
        }
    }
}

@Composable
fun EditingButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(horizontal = 5.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Cyan)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = text, fontSize = 14.sp)
        }
    }
}