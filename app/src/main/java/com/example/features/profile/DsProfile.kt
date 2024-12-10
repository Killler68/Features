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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.features.profile.viewmodel.ProfileViewModel
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel


@Composable
fun DsProfile(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        DsProfileActionBar(navController)
        DsLine()
        DsProfilePreview()
        DsLine()
        DsProfileInformation()
    }

}

@Composable
fun DsProfileActionBar(navController: NavController) {

    val viewModel: ProfileViewModel = getViewModel()


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
                .clickable { navController.navigate(Screens.Features.route) }
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

            Text(
                text = "email",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
            )
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

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var nationaly by remember { mutableStateOf("") }

    val viewModel: ProfileViewModel = getViewModel()

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {

        when (viewModel.num.intValue) {

            0 -> {


                Text(
                    text = "Андрей",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Text(
                    text = "Имя",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()
                Text(
                    text = "23",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Text(
                    text = "Возраст",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()
                Text(
                    text = "Русский",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Text(
                    text = "Национальность",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()


            }

            1 -> {

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    colors = OutlinedTextFieldDefaults.colors()
                )

                Text(
                    text = "Имя",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()
                TextField(
                    value = age,
                    onValueChange = { age = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    colors = OutlinedTextFieldDefaults.colors()
                )
                Text(
                    text = "Возраст",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()

                TextField(
                    value = city,
                    onValueChange = { city = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    colors = OutlinedTextFieldDefaults.colors()
                )
                Text(
                    text = "Город",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                DsLine()

                TextField(
                    value = nationaly,
                    onValueChange = { nationaly = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    colors = OutlinedTextFieldDefaults.colors()
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