package com.example.features.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.features.R

@Composable
fun DsWeather() {

    Column(
        Modifier
            .fillMaxSize()
    ) {
        DsWeatherActionBar()
    }

}

@Composable
fun DsWeatherActionBar() {

    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Text(text = "Буденновск")
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "image"
        )
    }
}