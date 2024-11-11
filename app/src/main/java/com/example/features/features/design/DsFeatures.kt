package com.example.features.features.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.features.MainScreenEvent
import com.example.features.MainViewModel
import com.example.features.R
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.viewmodel.FeaturesViewModel
import com.example.features.navigation.Screens
import com.example.features.ui.theme.Gray
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel

@Composable
fun DsFeatures(navController: NavController) {

    val mainViewModel: MainViewModel = viewModel()
    val viewModel: FeaturesViewModel = getViewModel()

    val sharedViewModel: SharedViewModel = getViewModel()
    val user by sharedViewModel.currentUser.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Gray)

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
                user?.login?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        maxLines = 1
                    )
                } ?: Text("No user logged in")

            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(LightGray)
        ) {
            items(viewModel.loadFeatures()) { feature ->
                DsFeatureItems(feature) {
                    navController.navigate(feature.feature)
                }

            }
        }
    }
}


