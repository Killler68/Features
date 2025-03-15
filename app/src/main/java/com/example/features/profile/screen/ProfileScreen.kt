package com.example.features.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.design.TopBarScreen
import com.example.features.profile.model.ProfileEvent
import com.example.features.profile.model.ProfileSideEffect
import com.example.features.profile.screen.view.ProfileIsEnabledOption
import com.example.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(userId: Int, navController: NavController) {
    val viewModel: ProfileViewModel = getViewModel()

    LaunchedEffect(userId) {
        viewModel.handleEvent(ProfileEvent.LoadProfile(userId))
    }
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProfileSideEffect.NavigateTo -> navController.navigate(effect.route)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarScreen(
                        R.drawable.back,
                        "back",
                        { viewModel.handleEvent((ProfileEvent.OnClickBack)) },
                        { viewModel.handleEvent(ProfileEvent.OnClickSettings) },
                        { viewModel.handleEvent(ProfileEvent.OnClickBack) }
                    )
                },
            )
        }, content = {
            ProfileContent(paddingValues = it, viewModel = viewModel)
        }
    )
}

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel
) {
    val state by remember { derivedStateOf { viewModel.state } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .padding(horizontal = 10.dp)
    ) {

        when {
            state.errorMessage != null -> Text(state.errorMessage!!, color = Color.Red)
            state.profile != null -> {
                val profile = state.profile!!

                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "profile",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(160.dp)
                )
                ProfileIsEnabledOption(profile, state, viewModel)
            }
        }
    }
}