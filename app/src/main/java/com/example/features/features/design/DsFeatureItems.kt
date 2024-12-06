package com.example.features.features.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.features.features.model.Features
import com.example.features.ui.theme.Gray

@Composable
fun DsFeatureItems(features: Features, onClick: () -> Unit) {

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 80.dp, top = 80.dp, bottom = 40.dp, end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Gray)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .size(width = 200.dp, height = 300.dp),
        ) {
            Text(
                text = features.title,
                style = TextStyle(fontSize = 30.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()

            )

            Text(
                text = features.description,
                style = TextStyle(fontSize = 30.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            )

            Image(
                painter = rememberImagePainter(data = "https:" + features.image),
                contentDescription = "image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
            )
        }
    }
}
