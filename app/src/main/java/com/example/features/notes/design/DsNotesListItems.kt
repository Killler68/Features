package com.example.features.notes.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.notes.model.NotesModel

@Composable
fun DsNotesListItems(notesModel: NotesModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .weight(0.9f)
                ) {
                    Text(
                        text = notesModel.title,
                        fontSize = 16.sp,
                        style = TextStyle(Color.Black),
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .background(Color.Gray)
                    )
                }


                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(36.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = notesModel.description,
                    fontSize = 16.sp,
                    style = TextStyle(color = Color.LightGray),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(0.9f)
                )


                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(36.dp)
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .size(height = 1.dp, width = 100.dp)
            )
        }
    }
}