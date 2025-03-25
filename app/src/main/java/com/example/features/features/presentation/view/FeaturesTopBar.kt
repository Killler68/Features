package com.example.features.features.presentation.view

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturesTopBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = {
            TopBarScreen(
                R.drawable.menu,
                stringResource(R.string.menu_image_description),
                { scope.launch { drawerState.open() } },
                ""
            )
        }
    )
}