package com.example.features.features.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.extension.getRawNameFeaturesCityEngToRuExtension
import com.example.features.common.utils.ExitBackStack
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.viewmodel.FeaturesViewModel
import com.example.features.navigation.Screens
import com.example.features.profile.DsLine
import com.example.features.ui.theme.LightGray
import com.example.features.weather.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun DsFeatures(navController: NavController) {

    val featuresViewModel: FeaturesViewModel = getViewModel()

    val weatherViewModel: WeatherViewModel = getViewModel()

    LaunchedEffect(Unit) {
        weatherViewModel.loadWeather()
        featuresViewModel.getDrawerItems()
    }

    val sharedViewModel: SharedViewModel = getViewModel()
    val user by sharedViewModel.currentUser.collectAsState()

    val items = featuresViewModel.drawer.value
    val selectedItem = remember { mutableStateOf(items.getOrNull(0)) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ExitBackStack()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.DarkGray,
                drawerContentColor = Color.LightGray
            ) {
                items.forEach { item ->
                    TextButton(
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item

                            when (item.id) {
                                0, 1 -> navController.navigate(Screens.Profile.route)
                                2 -> navController.navigate(Screens.Authorization.route)
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                        ) {
                            when (item.id) {
                                0 -> {
                                    Image(
                                        painter = painterResource(item.image),
                                        contentDescription = "image",
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                                            .size(180.dp)
                                            .clip(CircleShape)
                                            .background(Color.White)
                                            .padding(20.dp),
                                        alignment = Alignment.Center,
                                    )
                                }

                                1 -> {
                                    user?.login?.let {
                                        Text(
                                            text = it,
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 10.dp),
                                            textAlign = TextAlign.Center,
                                            maxLines = 1
                                        )
                                    }
                                }

                                else -> {
                                    Row {
                                        Image(
                                            painter = painterResource(item.image),
                                            contentDescription = "image_drawer",
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(LightGray)
                                                .padding(7.dp)
                                        )
                                        Text(
                                            text = item.title,
                                            fontSize = 16.sp,
                                            color = Color.Gray,
                                            modifier = Modifier
                                                .padding(start = 10.dp, top = 5.dp)

                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        content = {

            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "image_menu",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clip(CircleShape)
                            .size(40.dp)
                            .clickable {
                                scope.launch { drawerState.open() }
                            }
                    )
                }

                DsLine()

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    itemsIndexed(featuresViewModel.loadFeatures()) { index, feature ->
                        DsFeatureItems(feature) {
                            if (index == 1) {
                                feature.title =
                                    "В ${
                                        weatherViewModel.previewBarWeather.value.city
                                            .getRawNameFeaturesCityEngToRuExtension()
                                    } сегодня"
                                feature.description =
                                    "${weatherViewModel.previewBarWeather.value.temp} C"
                                feature.image = weatherViewModel.previewBarWeather.value.icon
                            }
                            navController.navigate(feature.feature)
                        }
                    }
                }
            }
        }
    )
}



