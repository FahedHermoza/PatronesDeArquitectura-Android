package com.fahedhermoza.developer.examplenote01.view.activities.notes

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fahedhermoza.developer.examplenote01.R
import com.fahedhermoza.developer.examplenote01.view.adapters.NoteAdapter
import com.fahedhermoza.developer.examplenote01.view.activities.detailNotes.DetailNotesActivity
import com.fahedhermoza.developer.examplenote01.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_notes.*

/**
 * A placeholder fragment containing a simple view.
 */
class NotesActivityFragment : Fragment() {
    private var adapter = NoteAdapter(mutableListOf())
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)

        recyclerViewNotes.adapter = adapter
        showLoading()

        viewModel.getSavedNotes().observe(this, Observer { movies ->
            hideLoading()
            movies?.let {
                adapter.setNotes(movies)
            }
        })
        setupViews()
    }

    private fun showLoading() {
        recyclerViewNotes.isEnabled = false
        progressBarNotes.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        recyclerViewNotes.isEnabled = true
        progressBarNotes.visibility = View.GONE
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

    private fun navigationToDetailNotes(){
        val intent = Intent(context, DetailNotesActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_notes, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_all_delete){
            viewModel.deleteAllItems()
            showToast("Notas eliminadas")
        }else if (item.itemId == R.id.deleteMenuItem) {
            var noteSizeDeleted = adapter.selectedNotes.size
            viewModel.deleteTapped(adapter.selectedNotes)
            if(noteSizeDeleted == 1){
                showToast("Nota eliminada")
            }else{
                showToast("Notas eliminadas")
            }
        }
        return false
    }

    private fun showToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }


}
