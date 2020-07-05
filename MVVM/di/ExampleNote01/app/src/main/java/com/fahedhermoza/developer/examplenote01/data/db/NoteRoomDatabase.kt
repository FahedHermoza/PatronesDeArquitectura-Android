package com.fahedhermoza.developer.examplenote01.Models

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fahedhermoza.developer.examplenote01.models.DateConverters

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
/*
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }*/

    companion object {
        private val lock = Any()
        private const val DB_NAME = "note_database"
        private var INSTANCE: NoteRoomDatabase? = null

        fun getInstance(application: Application): NoteRoomDatabase {
            synchronized(NoteRoomDatabase.lock) {
                if (NoteRoomDatabase.INSTANCE == null) {
                    NoteRoomDatabase.INSTANCE =
                        Room.databaseBuilder(application, NoteRoomDatabase::class.java, NoteRoomDatabase.DB_NAME)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}