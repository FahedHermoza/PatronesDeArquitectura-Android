package com.fahedhermoza.developer.examplenote01.view.activities.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fahedhermoza.developer.examplenote01.R

import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)
    }
}
