package com.challenge.demodaggerhilt.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.model.User
import com.challenge.demodaggerhilt.usecases.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appUseCase: AppUseCase): BaseViewModel(){


    val successUserLiveData        : LiveData<User> get()   = _successUserLiveData
    private val _successUserLiveData    = MutableLiveData<User>()

    fun getUser(){
        Log.d("TAGUSER","getUserAppView")
            executeSuspendNotProgress {
                val response = appUseCase.getUser()
                Log.d("TAGUSER","getUsersucess")
                _successUserLiveData.postValue(response)
            }
    }


}