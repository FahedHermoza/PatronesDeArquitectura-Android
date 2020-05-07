package com.fahedhermoza.developer.examplenote01.activities.detailNotes


class DetailNotesContract {

    interface PresenterInterface {
        fun addNote(title: String, description: String, isNewNote: Boolean)
    }

    interface ViewInterface {
        fun showToast(string: String)
        fun returnToNotes()
    }
}