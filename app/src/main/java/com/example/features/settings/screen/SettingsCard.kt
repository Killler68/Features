package com.example.features.settings.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.ui.theme.Cyan


@Composable
fun SettingsCard(
    @DrawableRes imageCard: Int,
    imageDescription: String,
    title: String,
    subTitle: String
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .background(Cyan)
                .padding(vertical = 10.dp)
        ) {
            Image(
                painter = painterResource(imageCard),
                contentDescription = imageDescription,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .size(24.dp)
            )
            Text(
                title,
                fontSize = 14.sp,
                modifier = Modifier
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = subTitle,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                )
            }
        }
    }
}