package com.gobinda.notepad.domain.usecase

import com.gobinda.notepad.common.NoteRepository
import com.gobinda.notepad.common.toNote
import com.gobinda.notepad.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSingleNoteUseCase(private val repository: NoteRepository) {
    fun execute(noteId: Long): Flow<Note?> {
        return repository.getSingleNote(noteId).map { it?.toNote() }
    }
}