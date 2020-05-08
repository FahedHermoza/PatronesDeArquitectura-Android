package com.fahedhermoza.developer.examplenote01.activities.notes

import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.raywenderlich.wewatch.BaseTest
import com.raywenderlich.wewatch.RxImmediateSchedulerRule
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class NotesPresenterTest : BaseTest(){
    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mockActivity : NotesContract.ViewInterface

    @Mock
    private lateinit var mockDataSource : LocalDataSource

    lateinit var notesPresenter : NotesPresenter

    @Before
    fun setUp() {
        notesPresenter = NotesPresenter(viewInterface = mockActivity, dataSource = mockDataSource)
    }

    private val allNotes: List<Note>
        get() {
            val noteList = ArrayList<Note>()
            noteList.add(Note("Title1", "Description1", Date()))
            noteList.add(Note("Title2", "Description2", Date()))
            noteList.add(Note("Title3", "Description3", Date()))
            noteList.add(Note("Title4", "Description4", Date()))
            noteList.add(Note("Title5", "Description5", Date()))
            return noteList.toList()
        }

    @Test
    fun testGetMyNotesList() {
        val myNotes = allNotes
        Mockito.doReturn(Observable.just(myNotes)).`when`(mockDataSource).allNotes
        notesPresenter.getMyNotesList()
        Mockito.verify(mockDataSource).allNotes
        Mockito.verify(mockActivity).displayNotes(myNotes)
    }

    @Test
    fun testGetMyNotesListWithoutNotes() {
        val myNotes: List<Note> = emptyList()
        Mockito.doReturn(Observable.just(myNotes)).`when`(mockDataSource).allNotes
        notesPresenter.getMyNotesList()
        Mockito.verify(mockDataSource).allNotes
        Mockito.verify(mockActivity).displayWithoutNotes()
    }

    private val deletedHashSetSingle: HashSet<Note>
        get() {
            val deletedHashSet = HashSet<Note>()
            deletedHashSet.add(allNotes[1])
            return deletedHashSet
        }

    @Test
    fun testDeleteSingle() {
        var myDeleteHashSet = deletedHashSetSingle
        notesPresenter.onDeleteTapped(myDeleteHashSet)
        for(note in myDeleteHashSet)
            Mockito.verify(mockDataSource).delete(note)
        Mockito.verify(mockActivity).showToast("Nota eliminada")
    }

    private val deletedHashSetMultiple: HashSet<Note>
        get() {
            val deletedHashSet = HashSet<Note>()
            deletedHashSet.add(allNotes[2])
            deletedHashSet.add(allNotes[3])
            deletedHashSet.add(allNotes[4])
            return deletedHashSet
        }

    @Test
    fun testDeleteMultiple() {
        var myDeleteHashSet = deletedHashSetMultiple
        notesPresenter.onDeleteTapped(myDeleteHashSet)
        for(note in myDeleteHashSet)
            Mockito.verify(mockDataSource).delete(note)
        Mockito.verify(mockActivity).showToast("Notas eliminadas")
    }

    @Test
    fun testDeleteAll() {
        notesPresenter.onDeleteAllItems()
        Mockito.verify(mockDataSource).deleteAll()
        Mockito.verify(mockActivity).showToast("Notas eliminadas")
    }
}