package com.example.features.notes.noteadd.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.navigation.Screens
import com.example.features.notes.noteslist.screen.DsActionBar
import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.noteadd.viewmodel.NoteAddViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAddScreen(
    navController: NavController
) {

    val viewModel: NoteAddViewModel = getViewModel()

    val sharedViewModel: SharedViewModel = getViewModel()
    val currentUser by sharedViewModel.currentUser.collectAsState()
    val userId = currentUser?.id

    var editTitle by remember { mutableStateOf("") }
    var editDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsActionBar {
            if (editTitle.isNotEmpty() && editDescription.isNotEmpty() || editDescription.isNotEmpty()) {
                if (userId != null) {
                    viewModel.createNote(
                        NotesModel(
                            title = editTitle,
                            description = editDescription,
                            userId = userId
                        )
                    )
                }
                navController.navigate(Screens.NotesList.route)
                editTitle = ""
                editDescription = ""
            } else {
                navController.navigate(Screens.NotesList.route)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .size(height = 1.dp, width = 100.dp)
        )

        TextField(
            value = editTitle,
            onValueChange = { editTitle = it },
            placeholder = { Text("Заглавие", fontSize = 24.sp) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedIndicatorColor = Color.White,
                unfocusedTextColor = Color.Black,
                containerColor = Color.White
            ),
            textStyle = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .fillMaxWidth()
        )
        TextField(
            value = editDescription,
            onValueChange = { editDescription = it },
            placeholder = { Text(text = "Описание", fontSize = 18.sp) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedIndicatorColor = Color.White,
                unfocusedTextColor = Color.Black,
                containerColor = Color.White
            ),
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}