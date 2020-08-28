package com.fahedhermoza.developer.examplenote01.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NotesViewModel(private val repository: NoteRepository = NoteRepositoryImpl()): ViewModel() {

    private val allNotes = MediatorLiveData<List<Note>>()

    init{
        getAllNotes()
    }

    fun getSavedNotes() = allNotes

    fun getAllNotes() {
        allNotes.addSource(repository.getSavedNotes()) { notes ->
            allNotes.postValue(notes)
        }
    }

    fun deleteTapped(selectedNotes: HashSet<*>) = viewModelScope.launch(Dispatchers.IO) {
        for (note in selectedNotes) {
            repository.delete(note as Note)
        }
    }

    fun deleteAllItems() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}