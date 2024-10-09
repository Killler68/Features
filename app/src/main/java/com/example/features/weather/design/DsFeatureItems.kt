package com.example.features.weather.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.features.features.model.Features
import com.example.features.navigation.Screens

@Composable
fun DsFeatureItems(features: Features, navController: NavController) {

    Row(
        Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Screens.Weather.route) }
    ) {
        Text(text = features.title)
        Text(text = features.description)
    }
}