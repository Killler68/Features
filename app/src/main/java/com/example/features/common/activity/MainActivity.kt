package com.example.features.common.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.features.common.activity.viewmodel.MainViewModel
import com.example.features.common.navigation.NavigationAppHost
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = getViewModel()
            val localeState = viewModel.locale.collectAsState(initial = "")

            LaunchedEffect(Unit) {
                viewModel.checkLocale()
            }

            NavigationAppHost(checkLocale = localeState.value)
        }
    }
}
