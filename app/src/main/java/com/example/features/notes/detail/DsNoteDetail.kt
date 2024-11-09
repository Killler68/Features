package com.example.features.notes.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.features.notes.viewmodel.NotesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DsNoteDetail(noteId: Int) {

    val viewModel: NotesViewModel = getViewModel()

    val notes = viewModel.getNoteDetail(noteId)

    notes.let {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                "hello",
                style = TextStyle(
                    fontSize = 40.sp
                )
            )
            Text(
                "Hello",
                style = TextStyle(
                    fontSize = 40.sp
                )

            )
        }
    }

}