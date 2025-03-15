package com.example.features.profile.screen.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.database.profile.model.Profile


@Composable
fun ProfileAdditionalInfo(profile: Profile) {

    Text(
        text = "Имя",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(
                BorderStroke(width = 1.dp, Color.LightGray),
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            text = profile.name,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        )

    }
    Text(
        text = "Возраст",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold

    )
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(
                BorderStroke(width = 1.dp, Color.LightGray),
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            text = profile.age,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        )

    }
    Text(
        text = "Национальность",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold

    )
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(
                BorderStroke(width = 1.dp, Color.LightGray),
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            text = profile.nationality,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        )
    }
}