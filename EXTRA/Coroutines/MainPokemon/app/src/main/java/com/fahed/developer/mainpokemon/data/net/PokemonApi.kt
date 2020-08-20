package com.fahed.developer.mainpokemon.data.net

import com.fahed.developer.mainpokemon.data.model.PokemonResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PokemonApi {

    @Headers("Content-Type: application/json")
    @GET("pokemon")
    //fun retrivePokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<PokemonResponse>
    suspend fun retrivePokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): PokemonResponse

    companion object {
        var API_BASE_URL_PHOTO = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        var API_BASE_URL_PHOTO_SHINY = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"

    }
}