package com.fahedhermoza.developer.examplenote01.activities.detailNotes

import android.text.TextUtils
import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import java.util.*

class DetailNotesPresenter (private var viewInterface: DetailNotesContract.ViewInterface,
                            private var localDataSource: LocalDataSource): DetailNotesContract.PresenterInterface {

    override fun addNote(title: String, description: String, isNewNote: Boolean) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            viewInterface.showToast("Ingrese título y descripción")
        } else {
            var note = Note(title,description, Date())

            if(!isNewNote) {
                localDataSource.update(note)
            }else {
                localDataSource.insert(note)
            }
            viewInterface.returnToNotes()
        }
    }


}