package com.example.task7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task7.model.models.Note
import com.example.task7.repository.RepositoryImpl

class NoteContentViewModelFactory(private val repository: RepositoryImpl , private val note: Note?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        NoteContentViewModel(repository, note) as T
}