package com.fahedhermoza.developer.examplenote01.viewmodel

import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.data.NoteRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class DetailNotesViewModelTest{
    private lateinit var detailNotesViewModel: DetailNotesViewModel

    @Mock
    lateinit var repository: NoteRepository

    @Before
    fun setup() {
        detailNotesViewModel = DetailNotesViewModel(repository)
    }

    //This is not necessary, just for example
    @Test
    fun isNoteProperlySaved(){
        var note = Note("Title", "Description", Date())
        detailNotesViewModel.insertNote(note)
        Mockito.verify(repository).insert(note)
    }

    @Test
    fun isNoteProperlyUpdated(){
        var note = Note("Title", "Description", Date())
        detailNotesViewModel.updateNote(note)
        Mockito.verify(repository).update(note)
    }

    @Test
    fun canSaveOrUpdateNoteWithoutTitle(){
        var note = Note("", "Description", Date())
        var canSaveNote = detailNotesViewModel.canSaveNote(note)
        assertEquals(false, canSaveNote)
    }

    @Test
    fun canSaveOrUpdateNoteWithoutDescription(){
        var note = Note("Title", "", Date())
        var canSaveNote = detailNotesViewModel.canSaveNote(note)
        assertEquals(false, canSaveNote)
    }
}
