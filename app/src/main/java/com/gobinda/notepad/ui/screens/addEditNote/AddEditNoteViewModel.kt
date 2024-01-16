package com.gobinda.notepad.ui.screens.addEditNote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.notepad.domain.model.Note
import com.gobinda.notepad.domain.usecase.AddNoteException
import com.gobinda.notepad.domain.usecase.AddNoteUseCase
import com.gobinda.notepad.domain.usecase.GetSingleNoteUseCase
import com.gobinda.notepad.ui.navigation.AddOrEditNoteScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    getSingleNoteUseCase: GetSingleNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val savedState: SavedStateHandle
) : ViewModel() {

    private val _titleText = MutableStateFlow("")
    val titleText: StateFlow<String> = _titleText

    private val _contentText = MutableStateFlow("")
    val contentText: StateFlow<String> = _contentText

    private val _toastMessage = MutableSharedFlow<Int?>()
    val toastMessage: SharedFlow<Int?> = _toastMessage

    private val _shouldCloseScreen = MutableSharedFlow<Boolean?>()
    val shouldCloseScreen: SharedFlow<Boolean?> = _shouldCloseScreen

    private var relatedNoteId: Long = -1L

    private val _isEditingNote = MutableStateFlow<Boolean?>(null)
    val isEditingNote: StateFlow<Boolean?> = _isEditingNote

    init {
        viewModelScope.launch {
            relatedNoteId = savedState.get<Long>(AddOrEditNoteScreen.noteIdArg) ?: -1L
            _isEditingNote.emit(relatedNoteId != -1L)
            if (relatedNoteId == -1L) return@launch // -1 for add note screen
            getSingleNoteUseCase.execute(relatedNoteId).firstOrNull()?.let {
                _titleText.emit(it.title)
                _contentText.emit(it.content)
            }
        }
    }

    fun handleEvent(event: AddEditUiEvent) = viewModelScope.launch {
        when (event) {
            is AddEditUiEvent.UpdateTitle -> {
                _titleText.tryEmit(event.title)
            }

            is AddEditUiEvent.UpdateContent -> {
                _contentText.tryEmit(event.content)
            }

            AddEditUiEvent.SaveNote -> {
                try {
                    val currentNote = Note(
                        title = titleText.value,
                        content = contentText.value,
                        lastEditTime = System.currentTimeMillis(),
                        id = if (relatedNoteId == -1L) 0L else relatedNoteId
                    )
                    addNoteUseCase.execute(currentNote)
                    _shouldCloseScreen.emit(true)
                } catch (exception: AddNoteException) {
                    _toastMessage.emit(exception.reason)
                }
            }
        }
    }
}