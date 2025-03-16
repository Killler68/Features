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
    profile.name.takeIf { it.isNotEmpty() }?.let {
        ProfileInfoItem(label = "Имя", value = it)
    }
    profile.age.takeIf { it.isNotEmpty() }?.let {
        ProfileInfoItem(label = "Возраст", value = it)
    }
    profile.nationality.takeIf { it.isNotEmpty() }?.let {
        ProfileInfoItem(label = "Национальность", value = it)
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Text(
        text = label,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(
                BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}