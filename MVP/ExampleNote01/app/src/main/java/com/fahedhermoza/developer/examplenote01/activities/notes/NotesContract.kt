package com.fahedhermoza.developer.examplenote01.activities.notes

import com.fahedhermoza.developer.examplenote01.Models.Note
import java.util.HashSet

class NotesContract {

    interface PresenterInterface {
        fun getMyNotesList()
        fun onDeleteTapped(selectedNotes: HashSet<*>)
        fun onDeleteAllItems()
        fun stop()

    }

    interface ViewInterface {
        fun displayNotes(noteList: List<Note>?)
        fun showToast(string: String)
        fun navigationToDetailNotes()
    }
}