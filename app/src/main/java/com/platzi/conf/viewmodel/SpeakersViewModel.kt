package com.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Speaker
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService

class SpeakersViewModel : ViewModel() {// al igual q el vievmodel(o controlador) del cronograma
    val firestoreService = FirestoreService()
    var listSpeakers : MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()


    fun refresh() {
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase()  {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }

}
