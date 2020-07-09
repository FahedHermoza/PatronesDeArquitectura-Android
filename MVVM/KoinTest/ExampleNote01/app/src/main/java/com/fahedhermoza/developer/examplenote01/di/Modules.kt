package com.fahedhermoza.developer.examplenote01.di

import android.app.Application
import androidx.room.Room
import com.fahedhermoza.developer.examplenote01.Models.NoteDao
import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.Models.NoteRoomDatabase
import com.fahedhermoza.developer.examplenote01.data.NoteRepository
import com.fahedhermoza.developer.examplenote01.viewmodel.DetailNotesViewModel
import com.fahedhermoza.developer.examplenote01.viewmodel.NotesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*** Recurso: https://medium.com/@rkaishwarya5/mvvm-koin-and-architecture-components-in-kotlin-6251100e2285 ***/
val repositoryModule = module {

    single<NoteRepository>{NoteRepositoryImpl()}

}

val viewmodelModule = module {

    viewModel { NotesViewModel(get()) }
    viewModel { DetailNotesViewModel(get()) }

}


val dataBaseModule = module {
    /*** No soporta concurrencia ***/
/*
    fun provideDatabase(application: Application): NoteRoomDatabase {
        val DB_NAME = "note_database"
        return Room.databaseBuilder(application.baseContext,
            NoteRoomDatabase::class.java, DB_NAME).build()
    }


    fun provideNoteDao(database: NoteRoomDatabase): NoteDao {
        return  database.noteDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideNoteDao(get()) }
    */

    /*** Soporta concurrencia ***/
    val lock = Any()
    val DB_NAME = "note_database"
    var INSTANCE: NoteRoomDatabase? = null

    fun provideDatabase(application: Application): NoteRoomDatabase {
        synchronized(lock) {
            if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(application, NoteRoomDatabase::class.java, DB_NAME)
                            .build() }
        }
        return INSTANCE!!
    }

    fun provideNoteDao(database: NoteRoomDatabase): NoteDao {
        return  database.noteDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideNoteDao(get()) }

}