package com.fahedhermoza.developer.examplenote01.data

import androidx.lifecycle.LiveData
import com.fahedhermoza.developer.examplenote01.Models.Note

interface NoteRepository {

    fun getSavedNotes(): LiveData<List<Note>>

    fun insert(note: Note)

    fun delete(note: Note)

    fun deleteAll()

    fun update(note: Note)
}