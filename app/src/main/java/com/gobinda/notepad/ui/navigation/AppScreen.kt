package com.gobinda.notepad.ui.navigation

sealed class AppScreen(val route:String) {
    object NoteListScreen:AppScreen("note_list_screen")
    object ShowNoteScreen:AppScreen("show_note_screen")
    object AddOrEditNoteScreen:AppScreen("add_or_edit_note_screen")
}