package com.example.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.features.navigation.NavigationAppHost
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = getViewModel()
            val localeState = viewModel.locale.collectAsState(initial = "")
            if (localeState.value.isNotEmpty()) {
                NavigationAppHost(checkLocale = localeState.value)
            } else {
                Text("Loading...")
            }
            LaunchedEffect(Unit) {
                viewModel.checkLocale()
            }
        }
    }
}
