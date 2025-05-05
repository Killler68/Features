package com.example.features.common.view

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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
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
    }
}

@Composable
fun TopBarScreen(
    @DrawableRes imageOnBack: Int,
    imageDescriptionOnBack: String,
    onBack: () -> Unit,
    onClick: () -> Unit,
    onExit: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val items = listOf(stringResource(R.string.edit), stringResource(R.string.exit))
    var selectedItem by remember { mutableStateOf("") }
    val editText = stringResource(R.string.edit)
    val exitText = stringResource(R.string.exit)

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(imageOnBack),
                contentDescription = imageDescriptionOnBack,
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray)
                    .padding(7.dp)
                    .clickable { onBack() }
            )

            Box(modifier = Modifier.weight(0.7f))

            Box {
                Image(
                    painter = painterResource(R.drawable.option),
                    contentDescription = stringResource(R.string.option_image_description),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                        .padding(7.dp)
                        .clickable { expanded = true }

                )

                DropdownMenu(
                    expanded = expanded,
                    offset = DpOffset(10.dp, 10.dp),
                    onDismissRequest = { expanded = false }
                ) {

                    items.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                selectedItem = item
                                when (item) {
                                    editText -> onClick()
                                    exitText -> onExit()
                                }
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
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