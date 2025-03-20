package com.example.features.profile.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.profile.presentation.models.ProfileEvent
import com.example.features.profile.presentation.models.ProfileState
import com.example.features.profile.presentation.ProfileViewModel

@Composable
fun ProfileEditingAdditionalInfo(state: ProfileState, viewModel: ProfileViewModel) {
    ProfileTextField(
        label = "Имя",
        value = state.editName,
        onValueChange = { viewModel.dispatch(ProfileEvent.OnNameChange(it)) }
    )
    ProfileTextField(
        label = "Возраст",
        value = state.editAge,
        onValueChange = { viewModel.dispatch(ProfileEvent.OnAgeChange(it)) },
        keyboardType = KeyboardType.Number
    )
    ProfileTextField(
        label = "Город",
        value = state.editCity,
        onValueChange = { viewModel.dispatch(ProfileEvent.OnCityChange(it)) }
    )
    ProfileTextField(
        label = "Национальность",
        value = state.editNationality,
        onValueChange = { viewModel.dispatch(ProfileEvent.OnNationalityChange(it)) }
    )
    ProfileTextField(
        label = "email",
        value = state.editEmail,
        onValueChange = { viewModel.dispatch(ProfileEvent.OnEmailChange(it)) }
    )
}

@Composable
fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        },
        textStyle = TextStyle(fontSize = 14.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .border(
                BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(6.dp)
            ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}