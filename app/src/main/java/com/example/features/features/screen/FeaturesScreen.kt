package com.example.features.features.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.design.TopBarScreen
import com.example.features.common.utils.ExitBackStack
import com.example.features.common.view.ErrorScreen
import com.example.features.common.view.LoadingScreen
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesSideEffect
import com.example.features.features.model.FeaturesState
import com.example.features.features.screen.view.FeaturesDrawerSheet
import com.example.features.features.screen.view.FeaturesPager
import com.example.features.features.viewmodel.FeaturesViewModel
import com.example.features.weather.detailed.screen.view.BottomNameView
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
        FeaturesState.Loading -> LoadingScreen()
        is FeaturesState.Success -> FeaturesContent(currentState, viewModel::dispatch)
        is FeaturesState.Error -> ErrorScreen(R.drawable.loading, "Error: ${currentState.message}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturesContent(
    state: FeaturesState.Success,
    dispatch: (FeaturesEvent) -> Unit
) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ExitBackStack()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.DarkGray,
                drawerContentColor = Color.LightGray
            ) {
                FeaturesDrawerSheet(state)
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
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {

                        FeaturesPager(state = state, dispatch = dispatch)

                        BottomNameView(name = "Сборник приложений", textColor = Color.Black)
                    }
                }
            )
        }
    )
}