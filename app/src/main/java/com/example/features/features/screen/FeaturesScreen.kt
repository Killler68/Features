package com.example.features.features.screen

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.features.R
import com.example.features.common.design.TopBarScreen
import com.example.features.common.extension.extensionConditionWeather
import com.example.features.common.extension.extensionTemperatureWeather
import com.example.features.common.extension.getRawNameFeaturesCityEngToRuExtension
import com.example.features.common.utils.ExitBackStack
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesSideEffect
import com.example.features.features.model.FeaturesState
import com.example.features.features.viewmodel.FeaturesViewModel
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import com.example.features.welcome.screen.PageIndicators
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun FeaturesScreen(navController: NavController) {

    val viewModel: FeaturesViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    val effectFlow = viewModel.effect

    val sharedViewModel: SharedViewModel = getViewModel()
    val user by sharedViewModel.currentUser.collectAsState()

    LaunchedEffect(user?.id) {
        user?.id?.let { userId ->
            viewModel.dispatch(FeaturesEvent.LoadAllData(userId))
        }
    }

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is FeaturesSideEffect.NavigateTo -> navController.navigate(effect.route)
            }
        }
    }

    when (val currentState = state) {
        FeaturesState.Loading -> Text("Loading")
        is FeaturesState.Success -> FeaturesContent(currentState, viewModel::dispatch)
        is FeaturesState.Error -> Text("Error: ${currentState.message}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturesContent(
    state: FeaturesState.Success,
    dispatch: (FeaturesEvent) -> Unit
) {

    val viewModel: FeaturesViewModel = getViewModel()
    val scope = rememberCoroutineScope()

    val sharedViewModel: SharedViewModel = getViewModel()
    val user by sharedViewModel.currentUser.collectAsState()

    val items = state.itemDrawer
    val selectedItem = remember { mutableStateOf(items.getOrNull(0)) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

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
                                0, 1 -> user?.id?.let {
                                    viewModel.dispatch(FeaturesEvent.NavigateToProfile(it))
                                }

                                2 -> viewModel.dispatch(FeaturesEvent.NavigateToSettings)
                                3 -> viewModel.dispatch(FeaturesEvent.NavigateToAbout)
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
                                        contentDescription = "profile",
                                        colorFilter = ColorFilter.tint(LightGray),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                                            .size(180.dp),
                                        alignment = Alignment.Center
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
                                            contentDescription = "drawer",
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
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            TopBarScreen(
                                R.drawable.menu,
                                "menu",
                                { scope.launch { drawerState.open() } },
                                ""
                            )
                        }
                    )
                },
                content = {

                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {

                        FeaturesPager(state = state, dispatch = dispatch)

                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                text = "Сборник приложений",
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                            )
                        }
                    }
                }
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun FeaturesPager(state: FeaturesState.Success, dispatch: (FeaturesEvent) -> Unit) {

    val pagerState = rememberPagerState(pageCount = { state.itemFeature.size })

    HorizontalPager(pagerState) { page ->
        Column {
            val items = state.itemFeature[page]

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
                    .padding(start = 60.dp, end = 60.dp, top = 50.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Cyan)
                    .clickable {
                        dispatch(FeaturesEvent.NavigateToFeature(items.feature))
                    },
            ) {

                when (page) {
                    0 -> {
                        if (state.itemNote.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp, vertical = 10.dp)
                            ) {
                                Text(
                                    text = "Последняя заметка",
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = state.itemNote.last().title,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp)
                                )
                                Text(
                                    text = state.itemNote.last().description,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Ничего не создано",
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    1 -> {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 10.dp)
                        ) {
                            GlideImage(
                                model = state.itemWeather.description,
                                contentDescription = "condition_weather",
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(48.dp)
                            )
                            GlideImage(
                                model = state.itemWeather.temp,
                                contentDescription = "condition_weather",
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                                    .size(width = 100.dp, height = 160.dp)
                            )

                            Text(
                                text = "В ${state.itemWeather.city.getRawNameFeaturesCityEngToRuExtension()} сегодня ",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            )

                            Text(
                                text = "${state.itemWeather.temp.toInt()}°",
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            )
                        }
                    }
                }
            }

            if (state.itemNote.isNotEmpty()) {
                Text(
                    text = state.itemFeature[page].title,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
            } else {
                Text(
                    text = state.itemFeature[page].description,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }

            PageIndicators(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                count = state.itemFeature.size,
                currentPage = pagerState.currentPage
            )
        }
    }
}