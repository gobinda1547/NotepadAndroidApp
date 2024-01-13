package com.gobinda.notepad.domain.usecase

import com.gobinda.notepad.R
import com.gobinda.notepad.common.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {

    @Throws(DeleteNoteException::class)
    suspend fun execute(noteId: Long) {
        if (repository.deleteNote(noteId) <= 0) {
            throw DeleteNoteException(R.string.error_unknown_exception)
        }
    }
}

class DeleteNoteException(val reason: Int) : Exception()