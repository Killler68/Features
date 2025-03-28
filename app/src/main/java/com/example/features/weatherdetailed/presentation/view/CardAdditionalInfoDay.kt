package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState

@Composable
fun CardAdditionalInfoDay(
    width: Float,
    alignment: Alignment,
    startPadding: Dp,
    endPadding: Dp,
    info: String,
    subInfo: String,
    image: Int,
    state: WeatherDetailedState.Success
) {
    val cardColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.CARD)
    val imageColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.IMAGE)
    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)

    Box(
        modifier = Modifier
            .fillMaxWidth(width),
        contentAlignment = alignment
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = startPadding, end = endPadding)
                .size(width = 1.dp, 100.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = stringResource(R.string.additional_info),
                        colorFilter = ColorFilter.tint(imageColor),
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp, top = 3.dp)
                            .size(18.dp)
                    )
                    Text(
                        text = info,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp),
                        color = textColor
                    )
                }
                Text(
                    text = subInfo,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    color = textColor
                )
            }
        }
    }
}