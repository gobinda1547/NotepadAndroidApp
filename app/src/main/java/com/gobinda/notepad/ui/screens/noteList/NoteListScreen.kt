package com.gobinda.notepad.ui.screens.noteList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gobinda.notepad.domain.model.NoteAsListItem
import com.gobinda.notepad.ui.items.ActualNoteListView
import com.gobinda.notepad.ui.items.NoteListEmptyView
import com.gobinda.notepad.ui.navigation.AppScreen

private const val TAG = "NoteListScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    navController: NavController,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val noteItems: State<List<NoteAsListItem>?> = viewModel.noteList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                title = { Text(text = "Notepad") },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(AppScreen.AddOrEditNoteScreen.route) }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        ) {
            noteItems.value?.let { validItems ->
                when (validItems.isEmpty()) {
                    true -> NoteListEmptyView {
                        navController.navigate(AppScreen.AddOrEditNoteScreen.route)
                    }

                    else -> ActualNoteListView(noteItems = validItems) {
                        Log.d(TAG, "NoteListScreen: onItemClicked = $it")
                    }
                }
            }
        }
    }
}