package com.fahedhermoza.developer.examplenote01.activities.detailNotes

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.R
import kotlinx.android.synthetic.main.fragment_detail_notes.*
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class DetailNotesActivityFragment : Fragment(), DetailNotesContract.ViewInterface {

    private var titleNote: String? = null
    private var desriptionNote: String? = null
    private var isNewNote: Boolean = false
    private lateinit var detailNotesPresenter: DetailNotesContract.PresenterInterface

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
        setupPresenter()
        setupViews()
    }

    private fun setupPresenter(){
        var dataSource = LocalDataSource(activity!!.application)
        detailNotesPresenter = DetailNotesPresenter(this, dataSource)
    }

    private fun setupViews(){
        getAnswer()
        actionButtonSave()
    }

    private fun actionButtonSave() {
        buttonSave.setOnClickListener {
            detailNotesPresenter.addNote(editTextTitle.text.toString(),
                editTextDescription.text.toString(), isNewNote)
        }
    }

    override fun showToast(string: String) {
        Toast.makeText(activity, string, Toast.LENGTH_LONG).show()
    }

    override fun returnToNotes() {
        activity?.finish()
    }

}
