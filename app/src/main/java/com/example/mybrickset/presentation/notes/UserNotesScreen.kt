package com.example.mybrickset.presentation.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.presentation.component.UserNoteItem
import com.example.mybrickset.presentation.error.ErrorScreen

@Composable
fun UserNotesScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: UserNotesViewModel = hiltViewModel(),
) {
    val userNotesState by viewModel.userNotesState.collectAsState()
    val userSetState by viewModel.userSetsState.collectAsState()

    Column {
        userNotesState.let { notesState ->
            if (notesState.error.isNotBlank()) {
                ErrorScreen(message = notesState.error)
            } else if (userSetState.sets.isNotEmpty()) {
                userSetState.let { userSetState ->
                    if (userSetState.sets.size == userNotesState.notes.size) {
                        LazyColumn {
                            items(notesState.notes.size) { index ->
                                notesState.notes[index].let {
                                    UserNoteItem(
                                        set = userSetState.sets[index],
                                        note = it.notes,
                                        navigateToDetail = { }
                                    )
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}