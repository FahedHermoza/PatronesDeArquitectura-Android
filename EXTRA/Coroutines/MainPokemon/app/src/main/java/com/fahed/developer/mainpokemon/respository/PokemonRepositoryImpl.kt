/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.fahed.developer.mainpokemon.respository

import com.fahed.developer.mainpokemon.data.model.Pokemon
import com.fahed.developer.mainpokemon.data.net.OperationResult
import com.fahed.developer.mainpokemon.data.net.PokemonApi
import org.koin.core.KoinComponent
import org.koin.core.inject

class PokemonRepositoryImpl : PokemonRepository, KoinComponent {

  private val pokemonApi: PokemonApi by inject()

  override suspend fun retrievePokemons(offset: Int, limit: Int): OperationResult<Pokemon> {
    try {
      val response = pokemonApi.retrivePokemons(offset, limit)
      response?.let {
          return if(it.results!!.isNotEmpty()){
              var arrayPokemon = ArrayList<Pokemon>()
              for(item in it.results!!){
                  //https://pokeapi.co/api/v2/pokemon/1/
                  var urlParts = item.url.split("/")
                  var number: Int = urlParts[urlParts.lastIndex-1].toInt()

                  var pokemon = Pokemon(item.name, item.url, number)
                  arrayPokemon.add(pokemon)
              }
              OperationResult.Success(arrayPokemon.toList())
          }else{
              OperationResult.Success(listOf())
          }
      }
    }catch (e:Exception){
      return OperationResult.Error(e)
    }
  }

}