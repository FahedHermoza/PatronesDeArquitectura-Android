package com.fahed.developer.mainpokemon.data.net

interface OperationCallback {
    fun onSuccess(obj:Any?)
    fun onError(obj:Any?)
}