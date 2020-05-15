package com.fahedhermoza.developer.examplenote01.Models

import android.util.Log
import androidx.lifecycle.LiveData
import com.fahedhermoza.developer.examplenote01.data.NoteRepository
import com.fahedhermoza.developer.examplenote01.db
import kotlin.concurrent.thread

open class NoteRepositoryImpl : NoteRepository {

    private val noteDao: NoteDao = db.noteDao()
    open val allNotes: LiveData<List<Note>>

    init {
        allNotes = noteDao.getAllNotes()
    }

    override fun getSavedNotes(): LiveData<List<Note>> {
        return allNotes
    }

    override fun insert(note: Note) {
        thread {
            noteDao.insert(note)
            Log.e("TAG", "INSERTADO")
        }
    }

    override fun delete(note: Note) {
        thread {
            noteDao.delete(note.title)
        }
    }

    override fun deleteAll() {
        thread {
            noteDao.deleteAll()
        }
    }

    override fun update(note: Note) {
        thread {
            noteDao.update(note)
            Log.e("TAG", "ACTUALIZADO")
        }
    }

}