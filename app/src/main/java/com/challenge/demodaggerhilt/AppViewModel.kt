package com.challenge.demodaggerhilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appUseCase: AppUseCase): BaseViewModel(){


    val successUserLiveData        : LiveData<User> get()   = _successUserLiveData
    private val _successUserLiveData    = MutableLiveData<User>()

    fun getUser(){
            executeSuspendNotProgress {
                val response = appUseCase.getUser()
                _successUserLiveData.postValue(response)
            }
    }


}