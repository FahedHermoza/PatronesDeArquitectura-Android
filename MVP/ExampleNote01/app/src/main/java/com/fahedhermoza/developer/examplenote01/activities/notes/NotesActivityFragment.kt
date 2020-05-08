package com.fahedhermoza.developer.examplenote01.activities.notes

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahedhermoza.developer.examplenote01.activities.detailNotes.DetailNotesActivity
import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.R
import kotlinx.android.synthetic.main.fragment_notes.*

/**
 * A placeholder fragment containing a simple view.
 */
class NotesActivityFragment : Fragment(), NotesContract.ViewInterface {
    private lateinit var adapter : NoteAdapter
    private lateinit var notesPresenter: NotesContract.PresenterInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        notesPresenter.getMyNotesList()
    }

    override fun onStop() {
        super.onStop()
        notesPresenter.stop()
    }

    private fun setupPresenter() {
        var dataSource = LocalDataSource(activity!!.application)
        notesPresenter = NotesPresenter(this, dataSource)
    }

    private fun setupViews(){
        changeTitle()
        actionFabPlusNotes()
    }

    private fun changeTitle(){
        activity?.title = getString(R.string.title_activity_notes)
    }

    private fun actionFabPlusNotes(){
        fabPlusNotes.setOnClickListener {
            navigationToDetailNotes()
        }
    }

    override fun displayNotes(noteList: List<Note>?) {
        adapter = NoteAdapter(noteList!!.toMutableList())
        recyclerViewNotes.adapter = adapter
        recyclerViewNotes.layoutManager = LinearLayoutManager(activity)
        recyclerViewNotes.visibility = View.VISIBLE
    }

    override fun displayWithoutNotes() {
        recyclerViewNotes.visibility = View.INVISIBLE
    }

    override fun navigationToDetailNotes(){
        val intent = Intent(context, DetailNotesActivity::class.java)
        startActivity(intent)
    }

    override fun showToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_notes, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_all_delete){
            notesPresenter.onDeleteAllItems()
        }else if (item.itemId == R.id.deleteMenuItem) {
            notesPresenter.onDeleteTapped(adapter.selectedNotes)
        }
        return false
    }

}
