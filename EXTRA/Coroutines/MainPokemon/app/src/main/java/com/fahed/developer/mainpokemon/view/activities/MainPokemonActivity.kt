package com.fahed.developer.mainpokemon.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fahed.developer.mainpokemon.R

import kotlinx.android.synthetic.main.activity_main_pokemon.*

class MainPokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pokemon)
        setSupportActionBar(toolbar)

    }

}
