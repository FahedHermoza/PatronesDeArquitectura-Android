package com.fahedhermoza.developer.examplenote01.adapter

import android.content.Context
import android.content.Intent
import android.text.format.DateUtils
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahedhermoza.developer.examplenote01.DetailNotesActivity
import com.fahedhermoza.developer.examplenote01.Models.Note
import com.fahedhermoza.developer.examplenote01.R
import com.fahedhermoza.developer.examplenote01.utils.inflate
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter(private var listNotes: MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var selectedNotes = HashSet<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.recyclerview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotes[position], selectedNotes)
    }

    override fun getItemCount() = listNotes.size

    fun updateNotes(courses: MutableList<Note>) {
        for(publication in courses){
            this.listNotes.add(publication)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note, selectedNotes: HashSet<Note>) {
            itemView.textViewDate.text = transformDate(itemView.context, note.date)
            itemView.textViewTitle.text = note.title

            lateinit var description: String
            if(note.description.length<30)
                description = note.description
            else
                description = note.description.substring(0,30).plus("...")
            itemView.textViewDescription.text = description

            itemView.checkboxDelete.setOnClickListener {
                if (!selectedNotes.contains(note)) {
                    itemView.checkboxDelete.isChecked = true
                    selectedNotes.add(note)
                } else {
                    itemView.checkboxDelete.isChecked = false
                    selectedNotes.remove(note)
                }
            }

            itemView.textViewTitle.setOnClickListener {
                goToDetailNotesActivity(note)
            }

        }

        fun goToDetailNotesActivity(note: Note) {
            val myIntent = Intent(itemView.context, DetailNotesActivity::class.java)
            myIntent.putExtra("title",note.title)
            myIntent.putExtra("description",note.description)
            itemView.context.startActivity(myIntent)
        }

        fun transformDate(context: Context, date: Date): String{
            return DateUtils.formatDateTime(context, date.time, 0)
        }
    }
}