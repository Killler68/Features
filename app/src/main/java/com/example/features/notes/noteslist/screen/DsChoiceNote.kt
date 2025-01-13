package com.example.features.notes.noteslist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.features.R


@Composable
fun DsChoiceNote(
    onDismiss: () -> Unit,
    navigateToAddNote: () -> Unit,
    navigateTotodo: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Выберите",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable { navigateToAddNote() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.note),
                        contentDescription = "note",
                        modifier = Modifier
                            .padding(start = 20.dp, end = 10.dp, top = 5.dp, bottom = 10.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = "Добавить заметку",
                        fontSize = 18.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigateTotodo() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.note),
                        contentDescription = "to-do",
                        modifier = Modifier
                            .padding(start = 20.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                            .size(24.dp)
                    )
                    Text(
                        text = "Добавить задачу",
                        fontSize = 18.sp
                    )

                }
            }
        }
    }
}