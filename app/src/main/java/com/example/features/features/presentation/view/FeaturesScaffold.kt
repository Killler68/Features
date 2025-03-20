package com.example.features.features.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.features.features.presentation.models.FeaturesEvent
import com.example.features.features.presentation.models.FeaturesState
import com.example.features.common.view.BottomNameView


@Composable
fun FeaturesScaffold(
    drawerState: DrawerState,
    state: FeaturesState.Success,
    dispatch: (FeaturesEvent) -> Unit
) {
    Scaffold(
        topBar = { FeaturesTopBar(drawerState) },
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