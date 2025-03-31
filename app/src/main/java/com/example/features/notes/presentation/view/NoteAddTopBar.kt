package com.example.features.notes.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.notes.presentation.models.NoteAddEvent
import com.example.features.notes.presentation.viewmodel.NoteAddViewModel
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAddTopBar(
    userId: Int,
    title: String,
    description: String,
) {
    val viewModel: NoteAddViewModel = getViewModel()

    TopAppBar(title = {
        Column {
            Row {
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = stringResource(R.string.back_image_description),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGray)
                        .padding(7.dp)
                        .clickable {
                            if (title.isNotEmpty() || description.isNotEmpty()) {
                                viewModel.dispatch(
                                    NoteAddEvent.CreateNote(
                                        userId,
                                        title,
                                        description
                                    )
                                )
                            }
                            viewModel.dispatch(NoteAddEvent.OnClickBack(userId))
                        }
                )
                Text(
                    text = stringResource(R.string.add_note),
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
    )
}