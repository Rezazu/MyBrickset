package com.example.mybrickset.presentation.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.data.remote.dto.getsets.Set

@Composable
fun UserNotesScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: UserNotesViewModel = hiltViewModel(),
) {
    val userNotes = viewModel.userNotesState.collectAsState()

    Column {
        Text(text = "This is your notes")
        userNotes.value.notes.forEach {
            UserNoteItem(
                set = it.set,
                note = it.notes
            )
        }
    }


}

@Composable
fun UserNoteItem(
    set: Set,
    note: String,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = set.name)
        Text(text = note)
    }
}