package com.fahedhermoza.developer.examplenote01

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import kotlinx.android.synthetic.main.fragment_detail_notes.*
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class DetailNotesActivityFragment : Fragment() {

    private lateinit var dataSource: LocalDataSource
    private var titleNote: String? = null
    private var desriptionNote: String? = null
    private var isNewNote: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_notes, container, false)
    }

    private fun getAnswer() {
        titleNote = activity?.intent?.getStringExtra("title")
        desriptionNote = activity?.intent?.getStringExtra("description")

        if(titleNote != null && desriptionNote != null) {
            editTextTitle.setText(titleNote)
            editTextDescription.setText(desriptionNote)
        }else{
            isNewNote = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAnswer()
        dataSource = LocalDataSource(activity!!.application)

        buttonSave.setOnClickListener {
            addNote(editTextTitle.text.toString(), editTextDescription.text.toString(), isNewNote)
        }
    }

    private fun addNote(title: String, description: String, isNewNote: Boolean) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            showToast("Ingrese título y descripción")
        } else {
            var note = Note(title,description, Date())

            if(!isNewNote) {
                dataSource.update(note)
            }else {
                dataSource.insert(note)
            }
            returnToNotes()
        }
    }

    private fun showToast(string: String) {
        Toast.makeText(activity, string, Toast.LENGTH_LONG).show()
    }

    private fun returnToNotes() {
        activity?.finish()
    }
}
