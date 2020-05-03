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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_notes, container, false)
    }

    private fun getAnswer() {
        titleNote = activity?.intent?.getStringExtra("title")
        desriptionNote = activity?.intent?.getStringExtra("description")

        if(titleNote != null && desriptionNote != null) {
            editTextTitle.setText(titleNote)
            editTextDescription.setText(desriptionNote)
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAnswer()
        setup()

        buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(editTextTitle.text) || TextUtils.isEmpty(editTextDescription.text)) {
                Toast.makeText(activity, "Ingrese titulo y descripción", Toast.LENGTH_LONG).show()
            } else {
                var title = editTextTitle.text.toString()
                var description = editTextDescription.text.toString()
                var note = Note(title,description, Date())

                if(titleNote != null && desriptionNote != null) {
                    dataSource.update(note)
                }else {
                    dataSource.insert(note)
                }

                activity?.finish()
            }

        }
    }

    private fun setup(){
        dataSource = LocalDataSource(activity!!.application)
    }
}
