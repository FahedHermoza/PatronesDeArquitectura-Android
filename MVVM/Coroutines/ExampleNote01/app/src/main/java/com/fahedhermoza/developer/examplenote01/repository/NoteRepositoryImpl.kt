package com.fahedhermoza.developer.examplenote01.Models

import android.util.Log
import androidx.lifecycle.LiveData
import com.fahedhermoza.developer.examplenote01.repository.NoteRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

open class NoteRepositoryImpl : NoteRepository, KoinComponent {

    private val noteDao: NoteDao by inject()

    open val allNotes: LiveData<List<Note>>

    init {
        allNotes = noteDao.getAllNotes()
    }

    override fun getSavedNotes(): LiveData<List<Note>> {
        return allNotes
    }

    override suspend fun insert(note: Note) {
       noteDao.insert(note)
       Log.e("TAG", "INSERTADO")
    }

    override suspend fun delete(note: Note) {
       noteDao.delete(note.title)
    }

    override suspend fun deleteAll() {
       noteDao.deleteAll()
    }

    override suspend fun update(note: Note) {
        noteDao.update(note)
        Log.e("TAG", "ACTUALIZADO")
    }

}