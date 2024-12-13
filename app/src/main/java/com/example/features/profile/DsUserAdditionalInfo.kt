package com.example.features.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.navigation.Screens
import com.example.features.profile.viewmodel.UserAdditionalInfoViewModel
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel


@Composable
fun DsUserAdditionalInfo(navController: NavController, userId: Int) {

    val viewModel: UserAdditionalInfoViewModel = getViewModel()

    LaunchedEffect(userId) {
        viewModel.loadUserInfo(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        DsProfileActionBar(navController, userId)
        DsLine()
        DsProfilePreview()
        DsLine()
        DsProfileInformation()
    }
//    LaunchedEffect(userId) {
//        userAdditionalInfoViewModel.loadUserAdditionalInfo(userId)
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            userAdditionalInfoViewModel.createUserAdditionalInfo(userId)
//        }
//    }

}

@Composable
fun DsProfileActionBar(navController: NavController, userId: Int) {

    val viewModel: UserAdditionalInfoViewModel = getViewModel()
//    val userId by viewModel.userAdditionalInfo.collectAsState()
    LaunchedEffect(userId) {
        viewModel.loadUserInfo(userId)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "image_back",
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .padding(7.dp)
                .clickable { navController.navigate(Screens.Features.createRoute(userId)) }
        )

        Text(
            text = "Профиль",
            fontSize = 16.sp,
            modifier = Modifier
                .weight(0.7f)
                .padding(10.dp)
        )

        when (viewModel.num.intValue) {

            0 -> {
                Image(
                    painter = painterResource(R.drawable.pencil),
                    contentDescription = "image_setting",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                        .padding(7.dp)
                        .clickable {
                            if (viewModel.num.intValue == 0) viewModel.num.intValue =
                                1 else viewModel.num.intValue = 0
                        }
                )
            }

            1 -> {
                Image(
                    painter = painterResource(R.drawable.trash_bucket),
                    contentDescription = "image_cancel",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                        .padding(7.dp)
                        .clickable {
                            if (viewModel.num.intValue == 0) viewModel.num.intValue =
                                1 else viewModel.num.intValue = 0
                        }
                )
                Image(
                    painter = painterResource(R.drawable.note),
                    contentDescription = "image_apply",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                        .padding(7.dp)
                        .clickable {
                            viewModel.addAdditionalInfo(
                                userId = userId,
                                viewModel.name.value,
                                viewModel.age.value,
                                viewModel.city.value,
                                viewModel.nationality.value
                            )
                            if (viewModel.num.intValue == 0) viewModel.num.intValue =
                                1 else viewModel.num.intValue = 0
                        }
                )
            }
        }
    }
}

@Composable
fun DsLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(horizontal = 100.dp, vertical = 0.5.dp)

    )
}

@Composable
fun DsProfilePreview() {

    val sharedViewModel: SharedViewModel = getViewModel()
    val user = sharedViewModel.currentUser.collectAsState()

    val userAdditionalInfoViewModel: UserAdditionalInfoViewModel = getViewModel()
    val userAdditionalInfo = userAdditionalInfoViewModel.currentUserAdditionalInfo.collectAsState()

    Row(
        modifier = Modifier
            .padding(10.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.note),
            contentDescription = "image_profile",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .padding(15.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {

            when (userAdditionalInfoViewModel.num.intValue) {
                0 -> {

                    if (userAdditionalInfoViewModel.email.value.isEmpty()) {
                        Text(
                            text = "email",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                        )
                    } else
                        userAdditionalInfo.value?.email?.let {
                            Text(
                                text = it,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            )
                        }
                }

                1 -> {
                    TextField(
                        value = userAdditionalInfoViewModel.email.value,
                        onValueChange = { userAdditionalInfoViewModel.email.value = it },
                        label = { Text(text = "email") },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LightGray,
                            focusedContainerColor = LightGray
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }



            DsLine()
            user.value?.login?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    color = Cyan,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            } ?: Text("User not Found")
        }
    }
}

@Composable
fun DsProfileInformation() {


    val userAdditionalInfoViewModel: UserAdditionalInfoViewModel = getViewModel()
    val userAdditionalInfo = userAdditionalInfoViewModel.currentUserAdditionalInfo.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {

        when (userAdditionalInfoViewModel.num.intValue) {

            0 -> {
                userAdditionalInfo.value?.let { info ->
                    Text(
                        text = info.name,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Имя",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    DsLine()
                    Text(
                        text = info.age,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Возраст",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    DsLine()
                    Text(
                        text = info.city,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Город",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    DsLine()
                    Text(
                        text = info.nationality,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Национальность",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    DsLine()
                } ?: Text("Данные не найдены.")

            }

            1 -> {

                TextField(
                    value = userAdditionalInfoViewModel.name.value,
                    onValueChange = { userAdditionalInfoViewModel.name.value = it },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Text(
                    text = "Имя",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()
                TextField(
                    value = userAdditionalInfoViewModel.age.value,
                    onValueChange = { userAdditionalInfoViewModel.age.value = it },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = "Возраст",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()

                TextField(
                    value = userAdditionalInfoViewModel.city.value,
                    onValueChange = { userAdditionalInfoViewModel.city.value = it },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = "Город",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()

                TextField(
                    value = userAdditionalInfoViewModel.nationality.value,
                    onValueChange = { userAdditionalInfoViewModel.nationality.value = it },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LightGray,
                        focusedContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = "Национальность",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()
            }
        }
    }
}