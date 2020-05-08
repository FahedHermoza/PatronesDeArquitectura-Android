package com.fahedhermoza.developer.examplenote01.activities.detailNotes

import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import java.util.*

class DetailNotesPresenter (private var viewInterface: DetailNotesContract.ViewInterface,
                            private var dataSource: LocalDataSource): DetailNotesContract.PresenterInterface {

    override fun addNote(title: String, description: String, isNewNote: Boolean) {
        if (title.isEmpty() || description.isEmpty()) {
            viewInterface.showToast("Ingrese título y descripción")
        } else {
            var note = Note(title,description, Date())

            if(!isNewNote) {
                dataSource.update(note)
            }else {
                dataSource.insert(note)
            }
            viewInterface.returnToNotes()
        }
    }


}