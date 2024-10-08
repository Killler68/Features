package com.example.features.features

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.MainScreenEvent
import com.example.features.MainViewModel
import com.example.features.R
import com.example.features.design.DsFeatureItems
import com.example.features.features.model.Features
import com.example.features.navigation.Screens

@Composable
fun DsFeatures(navController: NavController, mainViewModel: MainViewModel) {

    Column(
        Modifier
            .fillMaxSize()

    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.LightGray)

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "image",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable {
                        navController.navigate(Screens.Weather.route)
                        mainViewModel.handleEvent(MainScreenEvent.NavigateToWeather)
                    }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Имя пользоватеdsssssssssssssssssssssssssssssssssssssssssssssssля",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    maxLines = 1
                )

            }
        }

        LazyRow(

        ) {
            itemsIndexed(
                listOf(
                    Features(0, "1", "1", "1"),
                    Features(0, "2", "1", "1"),
                    Features(0, "3", "1", "1"),

                    )
            ) { index, item ->
                DsFeatureItems(features = item, navController)
            }
        }

    }

}