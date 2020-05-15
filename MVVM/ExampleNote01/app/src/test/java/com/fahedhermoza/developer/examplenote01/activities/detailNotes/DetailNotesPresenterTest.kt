package com.fahedhermoza.developer.examplenote01.activities.detailNotes

import com.fahedhermoza.developer.examplenote01.Models.NoteRepositoryImpl
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.raywenderlich.wewatch.BaseTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailNotesPresenterTest : BaseTest() {

    @Mock
    private lateinit var mockActivity : DetailNotesContract.ViewInterface

    @Mock
    private lateinit var mockDataSource : NoteRepositoryImpl

    lateinit var detailNotesPresenter : DetailNotesPresenter

    @Before
    fun setUp() {
        detailNotesPresenter = DetailNotesPresenter(viewInterface = mockActivity, dataSource = mockDataSource)
    }

    @Test
    fun testAddMovieWithoutData() {
        detailNotesPresenter.addNote("", "", isNewNote = false)
        Mockito.verify(mockActivity).showToast("Ingrese título y descripción")
    }

    @Captor
    private lateinit var noteArgumentCaptor: ArgumentCaptor<Note>
    @Test
    fun testAddMovieWithDataAndInsertNote() {
        detailNotesPresenter.addNote("Title1", "Description1", isNewNote = true)
        Mockito.verify(mockDataSource).insert(captureArg(noteArgumentCaptor))
        assertEquals("Title1", noteArgumentCaptor.value.title)
        assertEquals("Description1", noteArgumentCaptor.value.description)
        Mockito.verify(mockActivity).returnToNotes()
    }


    @Test
    fun testAddMovieWithDataAndUpdateNote() {
        detailNotesPresenter.addNote("Title1", "Description1", isNewNote = false)
        Mockito.verify(mockDataSource).update(captureArg(noteArgumentCaptor))
        assertEquals("Title1", noteArgumentCaptor.value.title)
        assertEquals("Description1", noteArgumentCaptor.value.description)
        Mockito.verify(mockActivity).returnToNotes()
    }
}