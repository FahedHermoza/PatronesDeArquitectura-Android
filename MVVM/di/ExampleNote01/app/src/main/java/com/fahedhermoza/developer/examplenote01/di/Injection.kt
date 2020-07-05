package com.fahedhermoza.developer.examplenote01.di

import androidx.lifecycle.ViewModelProvider
import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.viewmodel.ViewModelFactory

object Injection {

    private val noteRepository: NoteRepositoryImpl = NoteRepositoryImpl()
    private val noteViewModelFactory = ViewModelFactory(noteRepository)
    private val detailNoteViewModelFactory = ViewModelFactory(noteRepository)

    fun providerRepository():NoteRepositoryImpl{
        return noteRepository
    }

    fun providerNoteViewModelFactory(): ViewModelProvider.Factory{
        return noteViewModelFactory
    }

    fun providerDetailNoteViewModelFactory(): ViewModelProvider.Factory{
        return detailNoteViewModelFactory
    }
}