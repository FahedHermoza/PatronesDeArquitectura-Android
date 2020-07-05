package com.fahedhermoza.developer.examplenote01.view.activities.detailNotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fahedhermoza.developer.examplenote01.R

import kotlinx.android.synthetic.main.activity_detail_notes.*

class DetailNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_notes)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
