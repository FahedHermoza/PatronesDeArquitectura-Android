package com.fahedhermoza.developer.examplenote01.viewmodel

import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.data.NoteRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class NotesViewModelTest {
    private lateinit var notesViewModel: NotesViewModel

    @Mock
    lateinit var repository: NoteRepository

    @Before
    fun setup() {
        notesViewModel = NotesViewModel(repository)
    }

    //This is not necessary, just for example
    @Test
    fun isNoteProperlyDeletedAll(){
        notesViewModel.deleteAllItems()
        Mockito.verify(repository).deleteAll()
    }

    @Test
    fun isNoteProperlyDeletedTapped(){
        var selectedNotes = HashSet<Note>()
        selectedNotes.add(Note("Title1", "Description1", Date()))
        selectedNotes.add(Note("Title2", "Description2", Date()))

        notesViewModel.deleteTapped(selectedNotes)
        for(item in selectedNotes){
            Mockito.verify(repository).delete(item)
        }
    }


}