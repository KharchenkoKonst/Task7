package com.example.task7.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task7.model.models.Note
import com.example.task7.repository.INoteRepository
import kotlinx.coroutines.launch

class TitleNotesViewModel(private val repository: INoteRepository) : ViewModel() {


    fun getAllNotes(): LiveData<List<Note>> {
        return repository.getAllData()
    }

    fun downloadRandomNote() {
        viewModelScope.launch {
            repository.downloadNote((1..100).random())
        }
    }

    fun filterBySearch(fragment: String): LiveData<List<Note>> {
        return repository.getDataByFilter(fragment)
//        val allData = repository.getAllData()
//        val filteredData = mutableListOf<Note>()
//
//        allData.value?.run {
//            for (it in this) {
//                if (it.title.contains(fragment, false) || it.content.contains(fragment, false)
//                ) {
//                    filteredData.add(it)
//                }
//            }
//        }
//        return filteredData
    }
}