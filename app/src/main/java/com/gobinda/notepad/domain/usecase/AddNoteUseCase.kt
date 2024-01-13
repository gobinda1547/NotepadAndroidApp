package com.gobinda.notepad.domain.usecase

import com.gobinda.notepad.R
import com.gobinda.notepad.common.toNoteModel
import com.gobinda.notepad.domain.model.Note
import com.gobinda.notepad.common.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {

    @Throws(AddNoteException::class)
    suspend fun execute(note: Note) {
        if (note.title.trim().isEmpty()) {
            throw AddNoteException(R.string.error_empty_title)
        }
        if (note.content.trim().isEmpty()) {
            throw AddNoteException(R.string.error_empty_content)
        }
        if (repository.addOrUpdateNote(note.toNoteModel()) <= 0) {
            throw AddNoteException(R.string.error_unknown_exception)
        }
    }
}

class AddNoteException(val reason: Int) : Exception()