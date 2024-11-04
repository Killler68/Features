package com.example.features.weather.design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.features.model.Features

@Composable
fun DsFeatureItems(features: Features, onClick: () -> Unit) {

    Row(
        Modifier
            .padding(horizontal = 100.dp)
            .clickable { onClick() }
            .fillMaxWidth()

    ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(

                    text = features.title,
                    style = TextStyle(
                        fontSize = 30.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Text(
                    text = features.description,
                    style = TextStyle(
                        fontSize = 30.sp
                    )
                )
            }



    }
}