package com.fahedhermoza.developer.examplenote01

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.adapter.NoteAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_notes.*
import java.util.HashSet

/**
 * A placeholder fragment containing a simple view.
 */
class NotesActivityFragment : Fragment() {
    private lateinit var adapter : NoteAdapter

    private lateinit var dataSource: LocalDataSource
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.title_activity_notes)

        dataSource = LocalDataSource(activity!!.application)

        fab.setOnClickListener {
            navigationToDetailNotes()
        }
    }

    override fun onStart() {
        super.onStart()
        getMyNotesList()

    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun getMyNotesList() {
        val myNotesDisposable = myNotesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        compositeDisposable.add(myNotesDisposable)
    }

    private val myNotesObservable: Observable<List<Note>>
        get() = dataSource.allNotes


    private val observer: DisposableObserver<List<Note>>
        get() = object : DisposableObserver<List<Note>>() {

            override fun onNext(noteList: List<Note>) {
                displayNotes(noteList)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                showToast("Cuaderno vacio")
            }

            override fun onComplete() {
                Log.d("TAG", "Completed")
            }
        }

    fun displayNotes(noteList: List<Note>?) {
        if (noteList == null || noteList.isEmpty()) {
            Log.d("TAG", "No notes to display")
            recyclerView.visibility = View.INVISIBLE
        } else {
            adapter = NoteAdapter(noteList.toMutableList())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.visibility = View.VISIBLE
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
            dataSource.deleteAll()
            Toast.makeText(activity, "Notas eliminadas", Toast.LENGTH_SHORT).show()
        }else if (item.itemId == R.id.deleteMenuItem) {
            onDeleteTapped(adapter.selectedNotes)
        }
        return false
    }

    private fun onDeleteTapped(selectedNotes: HashSet<*>) {
        for (note in selectedNotes) {
            dataSource.delete(note as Note)
        }
        if (selectedNotes.size == 1) {
            showToast("Nota eliminada")
        } else if (selectedNotes.size > 1) {
            showToast("Notas eliminadas")
        }
    }

    private fun showToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }
}
