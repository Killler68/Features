package com.example.features.common.extension.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.features.navigation.Screens
import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.NotesViewModel

@Composable
fun DsButton(
    contentAlignmentBox: Alignment,
    paddingBox: Dp,
    colorsButton: Color,
    widthButton: Dp,
    heightButton: Dp,
    navController: NavController,
    toScreen: Screens,
    textButton: String
) {
    Box(
        contentAlignment = contentAlignmentBox,
        modifier = Modifier
            .padding(paddingBox)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(colorsButton),
            modifier = Modifier.size(widthButton, heightButton),
            onClick = { navController.navigate(toScreen.route) }

        ) {
            Text(textButton)
        }
    }
}

@Composable
fun DsButton(
    contentAlignmentBox: Alignment,
    paddingBox: Dp,
    colorsButton: Color,
    widthButton: Dp,
    heightButton: Dp,
    navController: NavController,
    toScreen: Screens,
    textButton: String,
    viewModelNotes: NotesViewModel,
    inputTextTitle: String,
    inputTextDescription: String,
) {
    Box(
        contentAlignment = contentAlignmentBox,
        modifier = Modifier
            .padding(paddingBox)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(colorsButton),
            modifier = Modifier.size(widthButton, heightButton),
            onClick = {
                viewModelNotes.createNote(NotesModel(inputTextTitle, inputTextDescription))
                navController.navigate(toScreen.route)
            }
        ) {
            Text(textButton)
        }
    }
}