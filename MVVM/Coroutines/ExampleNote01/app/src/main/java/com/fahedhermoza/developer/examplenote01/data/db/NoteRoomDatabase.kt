package com.fahedhermoza.developer.examplenote01.Models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fahedhermoza.developer.examplenote01.models.Converters

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}

