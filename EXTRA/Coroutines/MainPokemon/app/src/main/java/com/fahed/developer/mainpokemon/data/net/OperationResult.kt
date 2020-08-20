package com.fahed.developer.mainpokemon.data.net

sealed class OperationResult<out T> {
    data class Success<T>(val data: List<T>?) : OperationResult<T>()
    data class Error(val exception:Exception?) : OperationResult<Nothing>()
}