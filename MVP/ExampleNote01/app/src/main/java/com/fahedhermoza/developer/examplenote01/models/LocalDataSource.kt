package com.fahedhermoza.developer.examplenote01.Models

import android.app.Application
import io.reactivex.Observable
import kotlin.concurrent.thread

open class LocalDataSource (application: Application) {

    private val noteDao: NoteDao

    open val allNotes: Observable<List<Note>>

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        noteDao = db.noteDao()
        allNotes = noteDao.getAllNotes()
    }


    fun insert(note: Note) {
        thread {
            noteDao.insert(note)
        }
    }

    fun delete(note: Note) {
        thread {
            noteDao.delete(note.title)
        }
    }

    fun deleteAll() {
        thread {
            noteDao.deleteAll()
        }
    }

    fun update(note: Note) {
        thread {
            noteDao.update(note)
        }
    }

}