package com.fahedhermoza.developer.examplenote01.viewmodel

import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.repository.NoteRepository
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class DetailNotesViewModelTest: KoinTest {
    private val detailNotesViewModel: DetailNotesViewModel by inject()

    @Mock
    lateinit var repository: NoteRepository

    @Before
    fun setup() {
        startKoin {
            loadKoinModules(module{
                single<NoteRepository>{repository}
                viewModel { DetailNotesViewModel(get()) }
            })
        }
    }

    //This is not necessary, just for example
    @Test
    fun `Note save properly`(){
        var note = Note("Title", "Description", Date())
        detailNotesViewModel.insertNote(note)
        Mockito.verify(repository).insert(note)
    }

    @Test
    fun `Note update properly`(){
        var note = Note("Title", "Description", Date())
        detailNotesViewModel.updateNote(note)
        Mockito.verify(repository).update(note)
    }

    @Test
    fun `Can save or update Note without title`(){
        var note = Note("", "Description", Date())
        var canSaveNote = detailNotesViewModel.canSaveNote(note)
        assertEquals(false, canSaveNote)
    }

    @Test
    fun `Can save or update Note without description`(){
        var note = Note("Title", "", Date())
        var canSaveNote = detailNotesViewModel.canSaveNote(note)
        assertEquals(false, canSaveNote)
    }

    @After
    fun after() {
        stopKoin()
    }
}
