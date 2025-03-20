package com.example.features.profile.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.profile.data.database.model.Profile


@Composable
fun ProfilePreviewInfo(profile: Profile) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
//        login?.let {
//            Text(
//                text = it,
//                fontSize = 20.sp,
//                color = Cyan
//            )
//        }

        Text(text = "LOGIN")
    }
    if (profile.email.isNotEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = profile.email,
                fontSize = 16.sp
            )
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Row {
            if (profile.city.isNotEmpty()) {
                Image(
                    painter = painterResource(R.drawable.location),
                    contentDescription = "location",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 6.dp)
                )

                Text(
                    text = profile.city,
                    fontSize = 16.sp,
                )
            }
        }
    }
}