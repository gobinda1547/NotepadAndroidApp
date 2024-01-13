package com.gobinda.notepad.common

object NoteUtil {
    fun getFirstNotEmptyLineWithTrim(input: String): String {
        input.split("\n").forEach {
            if (it.isNotEmpty()) return it.trim()
        }
        return "Empty"
    }
}