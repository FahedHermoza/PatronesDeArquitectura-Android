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


    open fun insert(note: Note) {
        thread {
            noteDao.insert(note)
        }
    }

    open fun delete(note: Note) {
        thread {
            noteDao.delete(note.title)
        }
    }

    open fun deleteAll() {
        thread {
            noteDao.deleteAll()
        }
    }

    open fun update(note: Note) {
        thread {
            noteDao.update(note)
        }
    }

}