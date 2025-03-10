package com.example.features.common.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(@DrawableRes image: Int, textError: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column {
            Image(
                painter = painterResource(image),
                contentDescription = "image_error",
                modifier = Modifier
                    .padding(10.dp)
                    .size(120.dp)
            )

            Text(
                text = textError,
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)

            )
        }

    }

}