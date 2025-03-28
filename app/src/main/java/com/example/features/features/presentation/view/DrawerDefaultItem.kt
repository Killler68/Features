package com.example.features.features.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.features.domain.entities.DrawerItem
import com.example.features.ui.theme.LightGray

@Composable
fun DrawerDefaultItem(item: DrawerItem) {
    Row {
        Image(
            painter = painterResource(item.image),
            contentDescription = stringResource(R.string.drawer_image_description),
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .padding(7.dp)
        )
        Text(
            text = stringResource(item.title),
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
    }
}