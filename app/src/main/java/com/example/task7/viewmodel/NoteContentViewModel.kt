package com.example.task7.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task7.model.models.Note
import com.example.task7.repository.INoteRepository
import com.example.task7.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NoteContentViewModel(private val repository: INoteRepository, private val note: Note?) :
    ViewModel() {

    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    val onSaveNoteSuccessful = SingleLiveEvent<Unit>()
    val onErrorSaveNote = SingleLiveEvent<Unit>()

    init {
        if (note != null) {
            title.value = note.title
            content.value = note.content
        } else {
            title.value = ""
            content.value = ""
        }
    }

    fun saveNote() {
        val title = this.title.value.toString()
        val content = this.content.value.toString()

        if (title.isEmpty() || content.isEmpty()) {
            onErrorSaveNote.call()
            return
        }

        when (note) {
            null -> {
                val note = Note(getCurrentDate(), title, content)
                viewModelScope.launch { repository.insertNote(note) }
            }
            else -> {
                note.setNoteTitle(title)
                note.setNoteContent(content)
                viewModelScope.launch { repository.updateNote(note) }
            }
        }
        onSaveNoteSuccessful.call()
        return
    }

    private fun getCurrentDate(): String {
        val date = SimpleDateFormat("dd.M.yyyy", Locale.ENGLISH)
        return date.format(Date())
    }
}