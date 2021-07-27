package com.example.task7.repository

import androidx.lifecycle.LiveData
import com.example.task7.model.models.Note


interface INoteRepository {
    suspend fun downloadNote(id: Int)
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    fun getDataByFilter(fragment: String): LiveData<List<Note>>
    fun getAllData(): LiveData<List<Note>>
}
