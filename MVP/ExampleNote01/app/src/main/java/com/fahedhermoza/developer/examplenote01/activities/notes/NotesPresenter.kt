package com.fahedhermoza.developer.examplenote01.activities.notes

import android.util.Log
import com.fahedhermoza.developer.examplenote01.Models.LocalDataSource
import com.fahedhermoza.developer.examplenote01.Models.Note
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.HashSet

class NotesPresenter(private var viewInterface: NotesContract.ViewInterface,
                     private var dataSource: LocalDataSource): NotesContract.PresenterInterface {
    private val compositeDisposable = CompositeDisposable()

    private val myNotesObservable: Observable<List<Note>>
        get() = dataSource.allNotes


    private val observer: DisposableObserver<List<Note>>
        get() = object : DisposableObserver<List<Note>>() {

            override fun onNext(noteList: List<Note>) {
                viewInterface.displayNotes(noteList)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                viewInterface.showToast("Cuaderno vacio")
            }

            override fun onComplete() {
                Log.d("TAG", "Completed")
            }
        }

    override fun getMyNotesList() {
        val myNotesDisposable = myNotesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        compositeDisposable.add(myNotesDisposable)
    }

    override fun onDeleteTapped(selectedNotes: HashSet<*>) {
        for (note in selectedNotes) {
            dataSource.delete(note as Note)
        }
        if (selectedNotes.size == 1) {
            viewInterface.showToast("Nota eliminada")
        } else if (selectedNotes.size > 1) {
            viewInterface.showToast("Notas eliminadas")
        }
    }

    override fun onDeleteAllItems() {
        dataSource.deleteAll()
        viewInterface.showToast( "Notas eliminadas")
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}