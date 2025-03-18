package com.example.features.features.screen

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.utils.ExitBackStack
import com.example.features.common.view.ErrorScreen
import com.example.features.common.view.LoadingScreen
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesSideEffect
import com.example.features.features.model.FeaturesState
import com.example.features.features.screen.view.FeaturesDrawerSheet
import com.example.features.features.screen.view.FeaturesScaffold
import com.example.features.features.viewmodel.FeaturesViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun FeaturesScreen(navController: NavController, userId: Int) {
    val viewModel: FeaturesViewModel = getViewModel { parametersOf(userId) }
    val state by viewModel.state.collectAsState()
    val effectFlow = viewModel.effect

    LaunchedEffect(userId) {
        viewModel.dispatch(FeaturesEvent.LoadAllData(userId))
    }

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is FeaturesSideEffect.NavigateTo -> navController.navigate(effect.route)
                is FeaturesSideEffect.NavigateToFeature -> navController.navigate(effect.route)
            }
        }
    }

    when (val currentState = state) {
        FeaturesState.Loading -> LoadingScreen()
        is FeaturesState.Success -> FeaturesContent(currentState, viewModel::dispatch, userId)
        is FeaturesState.Error -> ErrorScreen(R.drawable.loading, currentState.message)
    }
}

@Composable
fun FeaturesContent(state: FeaturesState.Success, dispatch: (FeaturesEvent) -> Unit, userId: Int) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ExitBackStack()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.DarkGray,
                drawerContentColor = Color.LightGray
            ) {
                FeaturesDrawerSheet(state, userId)
            }
        },
        content = { FeaturesScaffold(drawerState, state, dispatch) }
    )
}

