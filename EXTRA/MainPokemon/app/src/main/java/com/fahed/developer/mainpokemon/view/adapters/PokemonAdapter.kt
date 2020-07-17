package com.fahed.developer.mainpokemon.view.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fahed.developer.mainpokemon.R
import com.fahed.developer.mainpokemon.data.model.Pokemon
import com.fahed.developer.mainpokemon.data.net.PokemonApi
import com.fahed.developer.mainpokemon.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(private var pokemons:List<Pokemon>):RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.item_pokemon)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //render
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    fun update(data:List<Pokemon>){
        var listPokemon = arrayListOf<Pokemon>()
        listPokemon.addAll(pokemons)
        for (item in data)
            listPokemon.add(item)
        pokemons = listPokemon.toList()
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val textViewName:TextView = view.textViewNameItemPokemon
        private val imageView:ImageView = view.imageViewItemPokemon

        fun bind(pokemon:Pokemon){
            textViewName.text = pokemon.name
            Picasso.get().load(PokemonApi.API_BASE_URL_PHOTO_SHINY + pokemon.number + ".png").into(imageView)
        }
    }
}