package com.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Conference
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    val firestoreService = FirestoreService()//llamamos el servicio q creamos para firestore
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()//creamos una lista livedata
    var isLoading = MutableLiveData<Boolean>()//crearmos un livedata booleano para controlar la carga osea con esto
                    //si es verdadero pondremos q el loadeing dejara de carga y mostrara las conferencias lo usaremos
                    //como variable para el control del loading q lego usaremos

    fun refresh() {// con esta funion hacemos q llame a las conferencias de firebase
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object: Callback<List<Conference>> {//traemos las conferencias
            override fun onSuccess(result: List<Conference>?) {//funcion de la interfaz del servicio de firestore
                listSchedule.postValue(result)//en esta funcion hacemos q si trae las conferencias lo guarde en listschadle
                processFinished()
            }

            override fun onFailed(exception: Exception) {//la otra funcion de la interfaz del servicio de firestore
                processFinished()// y en esta funcion nos mandara una excepcion si algo falla en la recepcion
            }
        })
    }

    fun processFinished() {// con esto hacemos q el livedata boleanos isloading se vuelva verdadero
        isLoading.value = true
    }
}