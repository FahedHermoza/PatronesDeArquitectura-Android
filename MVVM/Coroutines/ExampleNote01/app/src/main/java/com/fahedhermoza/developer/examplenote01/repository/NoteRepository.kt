package com.fahedhermoza.developer.examplenote01.repository

import androidx.lifecycle.LiveData
import com.fahedhermoza.developer.examplenote01.Models.Note

interface NoteRepository {

    fun getSavedNotes(): LiveData<List<Note>>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

    suspend fun deleteAll()

    suspend fun update(note: Note)
}