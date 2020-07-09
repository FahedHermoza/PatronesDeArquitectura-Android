package com.fahedhermoza.developer.examplenote01.viewmodel

import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.data.NoteRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
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
class NotesViewModelTest: KoinTest {
    private val notesViewModel:NotesViewModel by inject()

    @Mock
    lateinit var repository: NoteRepository

    @Before
    fun setup() {
        startKoin {
            loadKoinModules(module{
                single<NoteRepository>{repository}
                viewModel { NotesViewModel(get()) }
            })
        }
    }

    //This is not necessary, just for example
    @Test
    fun `Note is properly deleted all`(){
        notesViewModel.deleteAllItems()
        Mockito.verify(repository).deleteAll()
    }

    @Test
    fun `Note is properly deleted item by item`(){
        var selectedNotes = HashSet<Note>()
        selectedNotes.add(Note("Title1", "Description1", Date()))
        selectedNotes.add(Note("Title2", "Description2", Date()))

        notesViewModel.deleteTapped(selectedNotes)
        for(item in selectedNotes){
            Mockito.verify(repository).delete(item)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

}