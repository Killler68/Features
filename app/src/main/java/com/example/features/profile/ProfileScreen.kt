package com.example.features.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.common.view.LoadingScreen
import com.example.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileScreen(userId: Int) {
    val viewModel: ProfileViewModel = getViewModel()
    val state by remember { derivedStateOf { viewModel.uiState } }

    LaunchedEffect(userId) {
        Log.d("ProfileScreen", "userId: $userId")
        viewModel.loadProfile(userId)
    }

    LaunchedEffect(state) {
        Log.d("ProfileScreen", "Current UI state: $state")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
//            state.isLoading -> LoadingScreen()
            state.errorMessage != null -> Text(state.errorMessage!!, color = Color.Red)
            state.profile != null -> {
                val profile = state.profile!!

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "Settings",
                        modifier = Modifier.clickable { viewModel.onEditClick() }
                    )
                }

                if (state.isEditing) {
                    TextField(value = state.editName, onValueChange = viewModel::onNameChange)
                    TextField(value = state.editAge, onValueChange = viewModel::onAgeChange)
                    TextField(value = state.editCity, onValueChange = viewModel::onCityChange)
                    TextField(value = state.editNationality, onValueChange = viewModel::onNationalityChange)

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.pencil),
                            contentDescription = "Apply",
                            modifier = Modifier.clickable { viewModel.onApplyClick() }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.trash_bucket),
                            contentDescription = "Cancel",
                            modifier = Modifier.clickable { viewModel.onCancelClick() }
                        )
                    }
                } else {
                    Text(text = "Имя: ${profile.name}")
                    Text(text = "Возраст: ${profile.age}")
                    Text(text = "Город: ${profile.city}")
                    Text(text = "Национальность: ${profile.nationality}")
                }
            }
        }
    }
}
//
//@Composable
//fun ProfileContent() {
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//
//        DsProfileActionBar()
//        DsLine()
//        DsProfilePreview()
//        DsLine()
//        DsProfileInformation()
//    }
//
//}
//
//enum class ItemButtonEditing {
//    START,
//    SETTINGS,
//    CANCEL
//}
//
//@Composable
//fun DsProfileActionBar() {
//
//    val viewModel: ProfileViewModel = getViewModel()
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 10.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(R.drawable.back),
//            contentDescription = "image_back",
//            modifier = Modifier
//                .size(32.dp)
//                .clip(RoundedCornerShape(12.dp))
//                .background(LightGray)
//                .padding(7.dp)
//                .clickable { viewModel.dispatch(event = ProfileEvent.ToBack) }
//        )
//
//        Text(
//            text = "Профиль",
//            fontSize = 16.sp,
//            modifier = Modifier
//                .weight(0.7f)
//                .padding(10.dp)
//        )
//
//        when (viewModel.effect) {
//
//            ItemButtonEditing.SETTINGS -> {
//                Image(
//                    painter = painterResource(R.drawable.pencil),
//                    contentDescription = "image_setting",
//                    modifier = Modifier
//                        .size(32.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                        .background(LightGray)
//                        .padding(7.dp)
//                        .clickable {
//                            if (ProfileEvent.OnClickSettings) ProfileEvent.OnClickCancel
//                            else ProfileEvent.OnClickSettings
//                        }
//                )
//            }
//
//            ItemButtonEditing.CANCEL -> {
//                Image(
//                    painter = painterResource(R.drawable.trash_bucket),
//                    contentDescription = "image_cancel",
//                    modifier = Modifier
//                        .padding(horizontal = 10.dp)
//                        .size(32.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                        .background(LightGray)
//                        .padding(7.dp)
//                        .clickable {
//                            if (ProfileEvent.OnClickSettings) ProfileEvent.OnClickCancel
//                            else ProfileEvent.OnClickSettings
//                        }
//                )
//                Image(
//                    painter = painterResource(R.drawable.note),
//                    contentDescription = "image_apply",
//                    modifier = Modifier
//                        .size(32.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                        .background(LightGray)
//                        .padding(7.dp)
//                        .clickable {
//
//                            TODO()
//
//                            if (ProfileEvent.OnClickSettings) ProfileEvent.OnClickCancel
//                            else ProfileEvent.OnClickSettings
//                        }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun DsLine() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.LightGray)
//            .padding(horizontal = 100.dp, vertical = 0.5.dp)
//
//    )
//}
//
//@Composable
//fun DsProfilePreview() {
//
//    val sharedViewModel: SharedViewModel = getViewModel()
//    val user = sharedViewModel.currentUser.collectAsState()
//
//    val viewModel: ProfileViewModel = getViewModel()
//
//    Row(
//        modifier = Modifier
//            .padding(10.dp)
//    ) {
//
//        Image(
//            painter = painterResource(R.drawable.note),
//            contentDescription = "image_profile",
//            modifier = Modifier
//                .size(100.dp)
//                .clip(CircleShape)
//                .background(Color.LightGray)
//                .padding(15.dp)
//        )
//
//        Column(
//            modifier = Modifier
//                .padding(start = 10.dp)
//        ) {
//
//            when (viewModel.effect) {
//                ItemButtonEditing.START -> {
//
//                    if (viewModel.email.value.isEmpty()) {
//                        Text(
//                            text = "email",
//                            fontSize = 18.sp,
//                            modifier = Modifier
//                                .padding(top = 10.dp, bottom = 10.dp)
//                        )
//                    } else
//                        userAdditionalInfo.value?.email?.let {
//                            Text(
//                                text = it,
//                                fontSize = 18.sp,
//                                modifier = Modifier
//                                    .padding(top = 10.dp, bottom = 10.dp)
//                            )
//                        }
//                }
//
//                ItemButtonEditing.SETTINGS -> {
//                    TextField(
//                        value = viewModel.email.value,
//                        onValueChange = { profileViewModel.email.value = it },
//                        label = { Text(text = "email") },
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedContainerColor = LightGray,
//                            focusedContainerColor = LightGray
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                            .clip(RoundedCornerShape(12.dp))
//                    )
//                }
//            }
//
//
//
//            DsLine()
//            user.value?.login?.let {
//                Text(
//                    text = it,
//                    fontSize = 18.sp,
//                    color = Cyan,
//                    modifier = Modifier
//                        .padding(top = 10.dp)
//                )
//            } ?: Text("User not Found")
//        }
//    }
//}
//
//@Composable
//fun DsProfileInformation() {
//
//
//    val viewModel: ProfileViewModel = getViewModel()
//
//    Column(
//        modifier = Modifier
//            .padding(horizontal = 10.dp)
//    ) {
//
//        when (viewModel.effect) {
//
//            ItemButtonEditing.START -> {
//                userAdditionalInfo.value?.let { info ->
//                    Text(
//                        text = info.name,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(top = 10.dp)
//                    )
//                    Text(
//                        text = "Имя",
//                        fontSize = 12.sp,
//                        modifier = Modifier.padding(vertical = 10.dp)
//                    )
//                    DsLine()
//                    Text(
//                        text = info.age,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(top = 10.dp)
//                    )
//                    Text(
//                        text = "Возраст",
//                        fontSize = 12.sp,
//                        modifier = Modifier.padding(vertical = 10.dp)
//                    )
//                    DsLine()
//                    Text(
//                        text = info.city,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(top = 10.dp)
//                    )
//                    Text(
//                        text = "Город",
//                        fontSize = 12.sp,
//                        modifier = Modifier.padding(vertical = 10.dp)
//                    )
//                    DsLine()
//                    Text(
//                        text = info.nationality,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(top = 10.dp)
//                    )
//                    Text(
//                        text = "Национальность",
//                        fontSize = 12.sp,
//                        modifier = Modifier.padding(vertical = 10.dp)
//                    )
//                    DsLine()
//                } ?: Text("Данные не найдены.")
//
//            }
//
//            ItemButtonEditing.SETTINGS -> {
//
//                TextField(
//                    value = viewModel.name.value,
//                    onValueChange = { viewModel.name.value = it },
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = LightGray,
//                        focusedContainerColor = LightGray
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                )
//
//                Text(
//                    text = "Имя",
//                    fontSize = 12.sp,
//                    modifier = Modifier
//                        .padding(vertical = 10.dp)
//                )
//                DsLine()
//                TextField(
//                    value = viewModel.age.value,
//                    onValueChange = { viewModel.age.value = it },
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = LightGray,
//                        focusedContainerColor = LightGray
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                )
//                Text(
//                    text = "Возраст",
//                    fontSize = 12.sp,
//                    modifier = Modifier
//                        .padding(vertical = 10.dp)
//                )
//                DsLine()
//
//                TextField(
//                    value = viewModel.city.value,
//                    onValueChange = { viewModel.city.value = it },
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = LightGray,
//                        focusedContainerColor = LightGray
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                )
//                Text(
//                    text = "Город",
//                    fontSize = 12.sp,
//                    modifier = Modifier
//                        .padding(vertical = 10.dp)
//                )
//                DsLine()
//
//                TextField(
//                    value = viewModel.nationality.value,
//                    onValueChange = { viewModel.nationality.value = it },
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = LightGray,
//                        focusedContainerColor = LightGray
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                        .clip(RoundedCornerShape(12.dp))
//                )
//                Text(
//                    text = "Национальность",
//                    fontSize = 12.sp,
//                    modifier = Modifier
//                        .padding(vertical = 10.dp)
//                )
//                DsLine()
//            }
//        }
//    }
//}