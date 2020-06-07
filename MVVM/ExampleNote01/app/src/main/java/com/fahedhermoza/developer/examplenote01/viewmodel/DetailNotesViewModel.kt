package com.fahedhermoza.developer.examplenote01.viewmodel

import androidx.lifecycle.ViewModel
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.data.NoteRepository

class DetailNotesViewModel(private val repository: NoteRepository = NoteRepositoryImpl()): ViewModel() {

    fun insertNote(note: Note) {
        if(canSaveNote(note))
            repository.insert(note)
    }

    fun updateNote(note: Note) {
        if(canSaveNote(note))
            repository.update(note)
    }

    fun canSaveNote(note: Note): Boolean {
        return note.title.isNotEmpty() && note.description.isNotEmpty()
    }
}