package com.gobinda.notepad.ui.screens.addEditNote

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.notepad.domain.model.Note
import com.gobinda.notepad.domain.usecase.AddNoteException
import com.gobinda.notepad.domain.usecase.AddNoteUseCase
import com.gobinda.notepad.domain.usecase.GetSingleNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddEditNoteViewModel"

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    getSingleNoteUseCase: GetSingleNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _titleText = MutableStateFlow("")
    val titleText: StateFlow<String> = _titleText

    private val _contentText = MutableStateFlow("")
    val contentText: StateFlow<String> = _contentText

    private val _toastMessage = MutableSharedFlow<Int?>()
    val toastMessage: SharedFlow<Int?> = _toastMessage

    private val _shouldCloseScreen = MutableSharedFlow<Boolean?>()
    val shouldCloseScreen: SharedFlow<Boolean?> = _shouldCloseScreen

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
                        lastEditTime = System.currentTimeMillis()
                    )
                    addNoteUseCase.execute(currentNote)
                    _shouldCloseScreen.emit(true)
                } catch (exception: AddNoteException) {
                    Log.d(TAG, "handleEvent: invoking error ========= $exception")
                    _toastMessage.emit(exception.reason)
                }
            }
        }
    }
}