package com.gobinda.notepad.ui.navigation

abstract class AppScreen(protected val routeHeader: String) {
    open val inputRoute: String = routeHeader
    fun outputRoute(map: Map<String, Any>): String {
        var outputRouteValue = inputRoute
        map.forEach { mapEntry ->
            val paramKey = "{${mapEntry.key}}"
            val paramValue = "${mapEntry.value}"
            outputRouteValue = outputRouteValue.replace(paramKey, paramValue)
        }
        return outputRouteValue
    }
}

object NoteListScreen : AppScreen("note_list_screen")

object ShowNoteScreen : AppScreen("show_note_screen") {
    const val noteIdArg = "note_id"
    override val inputRoute: String = "$routeHeader?$noteIdArg={$noteIdArg}"
}

object AddOrEditNoteScreen : AppScreen("add_edit_note_screen") {
    const val noteIdArg = "note_id"
    override val inputRoute: String = "$routeHeader?$noteIdArg={$noteIdArg}"
}