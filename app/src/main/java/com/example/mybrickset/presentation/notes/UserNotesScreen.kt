package com.example.mybrickset.presentation.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mybrickset.data.Resource
import com.example.mybrickset.data.remote.dto.getsets.Set
import com.example.mybrickset.presentation.component.LegoItemSmall
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
            } else if (notesState.notes.isNotEmpty()) {
                userSetState.let { userSetState ->
                    if (userSetState.sets.size + 1 == userNotesState.notes.size) {
                        LazyColumn {
                            items(notesState.notes.size) { index ->
                                notesState.notes[index].let {
                                    UserNoteItem(
                                        set = userSetState.sets[index],
                                        note = it.notes,
                                        navigateToDetail = { }
                                    )
//                                    Text(text = it.notes)
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                }
//                LazyColumn() {
//                    items(notesState.notes.size) { index ->
//                        notesState.notes.let {
//                            UserNoteItem(
//                                set = userNotesState.sets[index],
//                                note = userNotesState.notes[index].notes,
//                                navigateToDetail = { })
//                        }
//                    }
//                }
//                LazyColumn() {
//                    items(notesState.notes) { notes ->
//                        UserNoteItem(
//                            set = notes.sets,
//                            note = notes.notes,
//                            navigateToDetail = { }
//                        )
//                        HorizontalDivider()
//                    }
//                }
            }
        }
    }
}