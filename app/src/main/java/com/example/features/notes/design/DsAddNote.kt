package com.example.features.notes.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.features.navigation.Screens
import com.example.features.notes.model.NotesModel
import com.example.features.notes.viewmodel.NotesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DsAddNote(navController: NavController) {

    val viewModel: NotesViewModel = getViewModel()


    var inputTextTitle by remember { mutableStateOf("") }
    var inputTextDescription by remember { mutableStateOf("") }


    Column {

        TextField(
            value = inputTextTitle,
            onValueChange = { inputTextTitle = it }
        )


        TextField(
            value = inputTextDescription,
            onValueChange = { inputTextDescription = it }
        )

        Row {

            Button(onClick = { navController.navigate(Screens.NotesList.route) }) {

                Text("Назад")

            }


            Button(onClick = {
                viewModel.createNote(NotesModel(inputTextTitle, inputTextDescription))
                navController.navigate(Screens.NotesList.route)
            }) {
                Text("Добавить")
            }
        }

    }

}