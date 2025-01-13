package com.example.features.weather.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.features.weather.state.WeatherState
import com.example.features.weather.viewmodel.WeatherViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun LoadingScreen() {
    val viewModel: WeatherViewModel = getViewModel()
    val state = viewModel.state.collectAsState()
    var progress by remember { mutableFloatStateOf(0.0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.value) {
        if (state.value is WeatherState.Loading) {
            progress = 0f
            scope.launch {
                while (progress < 1f) {
                    progress += 0.1f
                    delay(100L)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(progress = progress)
    }
}