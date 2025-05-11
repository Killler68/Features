package com.example.features.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.R.drawable.back
import com.example.features.R.string.about_project
import com.example.features.R.string.back_image_description
import com.example.features.common.view.CenteredImage
import com.example.features.common.view.InfoRow
import com.example.features.common.view.TopBarScreen
import com.example.features.ui.theme.LightGreen

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarScreen(
                        imageOnBack = back,
                        imageDescriptionOnBack = stringResource(back_image_description),
                        onBack = { navController.popBackStack() },
                        nameScreen = stringResource(about_project)
                    )
                }
            )
        },
        content = { AboutContent(paddingValues = it) }
    )
}

@Composable
fun AboutContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp)
    ) {
        CenteredImage(
            imageRes = R.drawable.tree,
            description = stringResource(R.string.tree),
            imageSize = 128.dp,
            imageColor = LightGreen
        )
        InfoRow(
            label = stringResource(R.string.name_project),
            value = stringResource(R.string.features)
        )
        InfoRow(
            label = stringResource(R.string.version_text),
            value = stringResource(R.string.version)
        )
    }
}