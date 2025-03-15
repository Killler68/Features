package com.example.features.profile.screen.view

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
import com.example.features.profile.model.ProfileEvent
import com.example.features.profile.model.ProfileState
import com.example.features.profile.viewmodel.ProfileViewModel


@Composable
fun ProfileEditingAdditionalInfo(state: ProfileState, viewModel: ProfileViewModel) {
    TextField(
        value = state.editName,
        onValueChange = { viewModel.handleEvent(ProfileEvent.OnNameChange(it)) },
        label = {
            Text(
                text = "Имя",
                fontSize = 10.sp,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .border(
                BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(6.dp)
            ),
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.LightGray
        )

    )
    TextField(
        value = state.editAge,
        onValueChange = { viewModel.handleEvent(ProfileEvent.OnAgeChange(it)) },
        label = {
            Text(
                text = "Возраст",
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .border(
                BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(6.dp)
            ),
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    TextField(
        value = state.editCity,
        onValueChange = { viewModel.handleEvent(ProfileEvent.OnCityChange(it)) },
        label = {
            Text(
                text = "Город",
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
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
        )
    )
    TextField(
        value = state.editNationality,
        onValueChange = { viewModel.handleEvent(ProfileEvent.OnNationalityChange(it)) },
        label = {
            Text(
                text = "Национальность",
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
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
        )
    )
    TextField(
        value = state.editEmail,
        onValueChange = { viewModel.handleEvent(ProfileEvent.OnEmailChange(it)) },
        label = {
            Text(
                text = "email",
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
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
        )
    )
}