package com.example.task7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task7.repository.RepositoryImpl

class TitleNotesViewModelFactory(private val repository: RepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TitleNotesViewModel(repository) as T
}