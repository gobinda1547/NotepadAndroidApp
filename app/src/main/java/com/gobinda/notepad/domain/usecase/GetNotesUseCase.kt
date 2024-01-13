package com.gobinda.notepad.domain.usecase

import com.gobinda.notepad.common.NoteRepository
import com.gobinda.notepad.common.toNoteAsListItem
import com.gobinda.notepad.domain.model.NoteAsListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val repository: NoteRepository) {
    fun execute(): Flow<List<NoteAsListItem>> {
        return repository.getAllNotes().map { notes ->
            notes.sortedBy { it.lastEditTime }
        }.map { noteModelList -> noteModelList.map { it.toNoteAsListItem() } }
    }
}