package com.gobinda.notepad.ui.screens.noteList

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gobinda.notepad.domain.model.NoteAsListItem
import com.gobinda.notepad.ui.items.ActualNoteListView
import com.gobinda.notepad.ui.items.NoteListEmptyView

private const val TAG = "NoteListScreen"

@Composable
fun NoteListScreen(
    navController: NavController,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val noteItems: State<List<NoteAsListItem>?> = viewModel.noteList.collectAsState()

    noteItems.value?.let { validItems ->
        when (validItems.isEmpty()) {
            true -> NoteListEmptyView()
            else -> ActualNoteListView(noteItems = validItems) {
                Log.d(TAG, "NoteListScreen: onItemClicked = $it")
            }
        }
    }
}