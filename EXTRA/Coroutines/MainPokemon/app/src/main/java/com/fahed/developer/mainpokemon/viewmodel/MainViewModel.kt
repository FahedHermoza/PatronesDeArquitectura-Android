package com.fahed.developer.mainpokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahed.developer.mainpokemon.data.model.Pokemon
import com.fahed.developer.mainpokemon.data.net.OperationResult
import com.fahed.developer.mainpokemon.respository.PokemonRepository
import com.fahed.developer.mainpokemon.respository.PokemonRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: PokemonRepository = PokemonRepositoryImpl()): ViewModel() {

    companion object {
        var OFFSET_PAGINATION: Int = -20
        const val LIMIT_PAGINATION: Int = 20
    }

    private val _pokemons = MutableLiveData<List<Pokemon>>().apply { value = emptyList() }
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    fun loadPokemons(){
        OFFSET_PAGINATION += 20

        _isViewLoading.postValue(true)

        viewModelScope.launch {
            var  result: OperationResult<Pokemon> = repository.retrievePokemons(OFFSET_PAGINATION, LIMIT_PAGINATION)
            _isViewLoading.postValue(false)
            when(result){
                is OperationResult.Success ->{
                    _isViewLoading.postValue(false)
                    if(result.data.isNullOrEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _pokemons.value = result.data
                    }
                }
                is OperationResult.Error ->{
                    _isViewLoading.postValue(false)
                    _onMessageError.postValue(result.exception)

                }
            }
        }
    }

}