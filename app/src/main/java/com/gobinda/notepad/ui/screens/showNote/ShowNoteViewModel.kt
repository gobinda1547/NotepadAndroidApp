package com.gobinda.notepad.ui.screens.showNote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.notepad.domain.model.Note
import com.gobinda.notepad.domain.usecase.GetSingleNoteUseCase
import com.gobinda.notepad.ui.navigation.ShowNoteScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowNoteViewModel @Inject constructor(
    getSingleNoteUseCase: GetSingleNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _currentNote = MutableStateFlow<Note?>(null)
    val currentNote: StateFlow<Note?> = _currentNote

    init {
        viewModelScope.launch {
            val noteId = savedStateHandle.get<Long>(ShowNoteScreen.noteIdArg) ?: return@launch
            getSingleNoteUseCase.execute(noteId).collect { noteResult ->
                noteResult?.let { _currentNote.emit(it) }
            }
        }
    }
}