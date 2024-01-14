package com.gobinda.notepad.ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gobinda.notepad.domain.model.NoteAsListItem

@Composable
fun NoteViewAsListItem(
    noteAsListItem: NoteAsListItem,
    onItemClick: (itemId: Long) -> Unit
) {
    Text(
        modifier = Modifier
            .clickable { onItemClick(noteAsListItem.id) }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        text = noteAsListItem.title,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    )
}