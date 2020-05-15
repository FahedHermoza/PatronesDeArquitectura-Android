package com.fahedhermoza.developer.examplenote01.Models

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Observable

@Dao
interface NoteDao {
    @Query("SELECT * from note_table ORDER BY date DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * from note_table ORDER BY date DESC")
    fun getAlphabetizedNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Query("DELETE FROM note_table WHERE title = :title")
    fun delete(title: String?)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Update
    fun update(note: Note)
}


