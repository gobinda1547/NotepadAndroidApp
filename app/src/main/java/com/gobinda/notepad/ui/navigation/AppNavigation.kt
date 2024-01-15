package com.gobinda.notepad.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.notepad.ui.screens.addEditNote.AddEditNoteScreen
import com.gobinda.notepad.ui.screens.noteList.NoteListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.NoteListScreen.route) {
        composable(AppScreen.NoteListScreen.route) {
            NoteListScreen(navController = navController)
        }
        composable(AppScreen.AddOrEditNoteScreen.route) {
            AddEditNoteScreen(navController = navController)
        }
    }
}