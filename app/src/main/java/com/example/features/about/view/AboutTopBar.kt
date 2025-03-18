package com.example.features.about.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.view.TopBarScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutTopBar(navController: NavController) {
    TopAppBar(
        title = {
            TopBarScreen(
                R.drawable.back,
                "back",
                { navController.popBackStack() },
                "О приложении"
            )
        }
    )
}