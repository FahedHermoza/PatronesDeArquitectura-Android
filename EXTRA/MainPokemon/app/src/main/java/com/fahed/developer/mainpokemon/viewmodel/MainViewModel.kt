package com.fahed.developer.mainpokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahed.developer.mainpokemon.data.model.Pokemon
import com.fahed.developer.mainpokemon.data.net.OperationCallback
import com.fahed.developer.mainpokemon.respository.PokemonRepository
import com.fahed.developer.mainpokemon.respository.PokemonRepositoryImpl

class MainViewModel(private val repository: PokemonRepository = PokemonRepositoryImpl()): ViewModel() {

    companion object {
        var OFFSET_PAGINATION: Int = -20
        val LIMIT_PAGINATION: Int = 20
    }

    private val _pokemons = MutableLiveData<List<Pokemon>>().apply { value = emptyList() }
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    fun loadPokemons(){
        OFFSET_PAGINATION += 20

        _isViewLoading.postValue(true)
        repository.retrievePokemons(object: OperationCallback {
            override fun onError(obj: Any?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( obj)
            }

            override fun onSuccess(obj: Any?) {
                _isViewLoading.postValue(false)

                if(obj!=null && obj is List<*>){
                    if(obj.isEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _pokemons.value= obj as List<Pokemon>
                    }
                }
            }
        },OFFSET_PAGINATION, LIMIT_PAGINATION)
    }

    fun stop(){
        repository.cancel()
    }

}