package com.platzi.conf.network

import java.lang.Exception

interface Callback<T> { // creamos una interface y con T indicamos q puede ser cualquier tipo de dato

    //creamos 2 funciones en la interfaz para si falla o recibe los datos de la db
    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)
}