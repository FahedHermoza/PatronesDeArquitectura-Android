package com.fahedhermoza.developer.examplenote01.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.data.NoteRepository
import java.util.HashSet

class NotesViewModel(private val repository: NoteRepository = NoteRepositoryImpl()): ViewModel() {

    private val allNotes = MediatorLiveData<List<Note>>()

    init{
        getAllNotes()
    }

    fun getSavedNotes() = allNotes

    private fun getAllNotes() {
        allNotes.addSource(repository.getSavedNotes()) { notes ->
            allNotes.postValue(notes)
        }
    }

    fun deleteTapped(selectedNotes: HashSet<*>) {
        for (note in selectedNotes) {
            repository.delete(note as Note)
        }
    }

    fun deleteAllItems() {
        repository.deleteAll()
    }
}