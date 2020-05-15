package com.fahedhermoza.developer.examplenote01.view.activities.detailNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.R
import com.fahedhermoza.developer.examplenote01.viewmodel.DetailNotesViewModel
import kotlinx.android.synthetic.main.fragment_detail_notes.*
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class DetailNotesActivityFragment : Fragment(){

    private var titleNote: String? = null
    private var desriptionNote: String? = null
    private var isNewNote: Boolean = false
    private lateinit var viewModel: DetailNotesViewModel


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
        viewModel = ViewModelProviders.of(this).get(DetailNotesViewModel::class.java)

        setupViews()
    }

    private fun setupViews(){
        getAnswer()
        actionButtonSave()
    }

    private fun actionButtonSave() {
        buttonSave.setOnClickListener {

            var title = editTextTitle.text.toString()
            var description = editTextDescription.text.toString()
            if (title.isEmpty() || description.isEmpty()) {
                showToast("Ingrese título y descripción")
            } else {
                var note = Note(title,description, Date())

                if(!isNewNote) {
                    viewModel.updateNote(note)
                }else {
                    viewModel.insertNote(note)
                }
                returnToNotes()
            }
        }
    }

    private fun showToast(string: String) {
        Toast.makeText(activity, string, Toast.LENGTH_LONG).show()
    }

    private fun returnToNotes() {
        activity?.finish()
    }

}
