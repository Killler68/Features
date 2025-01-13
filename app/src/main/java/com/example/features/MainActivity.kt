package com.example.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.features.navigation.NavigationAppHost
import com.example.features.start.StartScreen
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = getViewModel()
            val localeState = viewModel.locale.collectAsState(initial = "")
            var showStartScreen by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(1000L)
                showStartScreen = false
                viewModel.checkLocale()
            }

            if (showStartScreen) {
                StartScreen()
            } else if (localeState.value.isNotEmpty()) {
                NavigationAppHost(checkLocale = localeState.value)
            }
        }
    }
}
