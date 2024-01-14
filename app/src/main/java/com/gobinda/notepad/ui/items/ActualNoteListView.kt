package com.gobinda.notepad.ui.items

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.gobinda.notepad.domain.model.NoteAsListItem

@Composable
fun ActualNoteListView(
    noteItems: List<NoteAsListItem>,
    onItemClicked: (itemId: Long) -> Unit
) {
    LazyColumn {
        items(
            count = noteItems.size,
            key = { i -> i }
        ) {
            NoteViewAsListItem(
                noteAsListItem = noteItems[it],
                onItemClick = onItemClicked
            )
            MenuDivider()
        }
    }
}