package com.example.features.features.presentation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.utils.ExitBackStack
import com.example.features.common.view.ErrorScreen
import com.example.features.common.view.LoadingScreen
import com.example.features.features.presentation.models.FeaturesEvent
import com.example.features.features.presentation.models.FeaturesSideEffect
import com.example.features.features.presentation.models.FeaturesState
import com.example.features.features.presentation.view.FeaturesDrawerSheet
import com.example.features.features.presentation.view.FeaturesScaffold
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun FeaturesScreen(navController: NavController, userId: Int) {
    val viewModel: FeaturesViewModel = getViewModel { parametersOf(userId) }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(userId) {
        viewModel.dispatch(FeaturesEvent.LoadAllData(userId))
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is FeaturesSideEffect.NavigateTo -> {
                    navController.navigate(effect.route)
                }
            }
        }
    }

    when (val currentState = state) {
        FeaturesState.Loading -> LoadingScreen()
        is FeaturesState.Success -> FeaturesContent(currentState, viewModel::dispatch, userId)
        is FeaturesState.Error -> ErrorScreen(
            R.drawable.loading,
            stringResource(R.string.error_load)
        )
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
        content = { FeaturesScaffold(userId, drawerState, state, dispatch) }
    )
}