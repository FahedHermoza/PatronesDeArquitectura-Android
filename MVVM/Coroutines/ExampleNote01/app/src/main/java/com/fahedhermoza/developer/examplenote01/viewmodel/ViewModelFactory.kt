package com.fahedhermoza.developer.examplenote01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fahedhermoza.developer.examplenote01.repository.NoteRepository

class ViewModelFactory(private val repository: NoteRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor ( NoteRepository:: class. java ) .newInstance (repository)
    }
}
