package com.example.features.notes.noteslist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.navigation.Screens
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import com.example.features.notes.task.viewmodel.TaskViewModel
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import org.koin.androidx.compose.getViewModel


@Composable
fun NotesListScreen(navController: NavController) {

    val viewModel: NotesViewModel = getViewModel()

    val onCLickChoiceNote = { viewModel.isChoiceNote.value = true }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsActionBar { navController.navigate(Screens.Features.route) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .size(height = 1.dp, width = 100.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {

            CombinedColumnList(navController)
            ChoiceScreen(navController)

            Image(
                imageVector = Icons.Filled.Add,
                contentDescription = "add_note",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 40.dp)
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(Cyan)
                    .padding(7.dp)
                    .align(Alignment.BottomEnd)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onCLickChoiceNote() }
                    )
            )
        }
    }
}


@Composable
fun DsActionBar(
    onClick: () -> Unit
) {

    val viewModel: NotesViewModel = getViewModel()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "image",
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .padding(7.dp)
                .clickable { onClick() }
        )

        Text(
            text = "Заметки",
            fontSize = 16.sp,
            modifier = Modifier
                .weight(0.7f)
                .padding(10.dp)
        )

        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "image",
            modifier = Modifier
                .size(50.dp)
                .clickable { viewModel.isAddNote.value = true }
        )
    }
}