package com.example.features.common.design

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.getRawNameCityEngToRuExtension
import com.example.features.ui.theme.LightGray


@Composable
fun TopBarScreen(
    @DrawableRes imageOnBack: Int,
    imageDescriptionOnBack: String,
    onBack: () -> Unit,
    nameScreen: String
) {
    Column {
        Row {
            Image(
                painter = painterResource(imageOnBack),
                contentDescription = imageDescriptionOnBack,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightGray)
                    .padding(7.dp)
                    .clickable { onBack() }
            )
            Text(
                text = nameScreen,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 10.dp, end = 15.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
                .size(height = 1.dp, width = 1.dp)
        )
    }
}

@Composable
fun TopBarScreen(
    @DrawableRes imageOnBack: Int,
    onBack: () -> Unit,
    imageDescriptionOnBack: String,
    city: String,
    textColor: Color
) {
    Column {
        Row {
            Image(
                painter = painterResource(imageOnBack),
                contentDescription = imageDescriptionOnBack,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(7.dp)
                    .clickable { onBack() }
            )
            Text(
                text = city.getRawNameCityEngToRuExtension(),
                fontSize = 18.sp,
                color = textColor
            )
        }
    }
}

@Composable
fun TopBarScreen(
    @DrawableRes imageOnBack: Int,
    onBack: () -> Unit,
    imageDescriptionOnBack: String,
    city: String,
    imageColor: Color,
    textColor: Color,
    @DrawableRes image: Int,
    imageDescription: String,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(imageOnBack),
                contentDescription = imageDescriptionOnBack,
                colorFilter = ColorFilter.tint(imageColor),
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(7.dp)
                    .clickable { onBack() }
            )
            Text(
                text = city.getRawNameCityEngToRuExtension(),
                fontSize = 18.sp,
                color = textColor,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = imageDescription,
                    colorFilter = ColorFilter.tint(imageColor),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(32.dp)
                        .padding(6.dp)
                        .clickable { onClick() }
                )
            }
        }
    }
}