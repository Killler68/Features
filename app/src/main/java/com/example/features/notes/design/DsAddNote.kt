package com.example.features.notes.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.features.common.extension.design.DsButton
import com.example.features.navigation.Screens
import com.example.features.notes.viewmodel.NotesViewModel
import com.example.features.ui.theme.Cyan
import org.koin.androidx.compose.getViewModel

@Composable
fun DsAddNote(navController: NavController) {

    var inputTextTitle by remember { mutableStateOf("") }
    var inputTextDescription by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.6f))
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth(0.85f)
                .background(Color.White)
        ) {
            TextField(
                value = inputTextTitle,
                onValueChange = { inputTextTitle = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                textStyle = TextStyle(Color.Black),
                colors = OutlinedTextFieldDefaults.colors(Color.White)
            )
            TextField(
                value = inputTextDescription,
                onValueChange = { inputTextDescription = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                textStyle = TextStyle(Color.Black),
                colors = OutlinedTextFieldDefaults.colors(Color.White)
            )
            DsRowButtonsAddNote(navController, inputTextTitle, inputTextDescription)
        }
    }
}

@Composable
fun DsRowButtonsAddNote(
    navController: NavController,
    inputTitle: String,
    inputDescription: String
) {
    val viewModel: NotesViewModel = getViewModel()

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        DsButton(
            contentAlignmentBox = Alignment.CenterStart,
            paddingBox = 10.dp,
            colorsButton = Cyan,
            widthButton = 140.dp,
            heightButton = 40.dp,
            navController = navController,
            toScreen = Screens.NotesList,
            textButton = "Назад"
        )
        DsButton(
            contentAlignmentBox = Alignment.CenterEnd,
            paddingBox = 10.dp,
            colorsButton = Cyan,
            widthButton = 140.dp,
            heightButton = 40.dp,
            navController = navController,
            toScreen = Screens.NotesList,
            textButton = "Добавить",
            viewModelNotes = viewModel,
            inputTextTitle = inputTitle,
            inputTextDescription = inputDescription
        )
    }
}