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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.features.model.Features
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.Gray

@Composable
fun DsFeatureItems(features: Features, onClick: () -> Unit) {

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(start = 80.dp, top = 80.dp, bottom = 40.dp, end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Gray)
            .clickable { onClick() }
    ) {
        Column {
            Text(
                text = features.title,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 30.sp),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(200.dp, 40.dp)
            )

            Text(
                text = features.description,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 30.sp),
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .size(200.dp, 40.dp),
            )

            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "image",
                modifier = Modifier
                    .size(200.dp)
            )
        }
    }
}
