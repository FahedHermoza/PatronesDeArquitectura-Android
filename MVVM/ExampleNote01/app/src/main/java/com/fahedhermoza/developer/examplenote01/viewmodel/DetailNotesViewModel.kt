package com.fahedhermoza.developer.examplenote01.viewmodel

import androidx.lifecycle.ViewModel
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.data.NoteRepository

class DetailNotesViewModel(private val repository: NoteRepository = NoteRepositoryImpl()): ViewModel() {

    fun insertNote(note: Note) {
        repository.insert(note)
    }

    fun updateNote(note: Note) {
        repository.update(note)
    }
}