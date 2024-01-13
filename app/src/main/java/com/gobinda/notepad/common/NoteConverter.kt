package com.gobinda.notepad.common

import com.gobinda.notepad.data.model.NoteModel
import com.gobinda.notepad.domain.model.Note
import com.gobinda.notepad.domain.model.NoteAsListItem

fun NoteModel.toNote(): Note {
    return Note(title, content, lastEditTime, id)
}

fun NoteModel.toNoteAsListItem(): NoteAsListItem {
    return NoteAsListItem(
        title = NoteUtil.getFirstNotEmptyLineWithTrim(title),
        id = id
    )
}

fun Note.toNoteModel(): NoteModel {
    return NoteModel(title, content, lastEditTime, id)
}