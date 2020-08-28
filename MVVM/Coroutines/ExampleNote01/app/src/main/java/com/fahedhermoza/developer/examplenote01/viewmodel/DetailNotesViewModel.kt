package com.fahedhermoza.developer.examplenote01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailNotesViewModel(private val repository: NoteRepository = NoteRepositoryImpl()): ViewModel() {

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        if(canSaveNote(note))
            repository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        if(canSaveNote(note))
            repository.update(note)
    }

    fun canSaveNote(note: Note): Boolean {
        return note.title.isNotEmpty() && note.description.isNotEmpty()
    }
}