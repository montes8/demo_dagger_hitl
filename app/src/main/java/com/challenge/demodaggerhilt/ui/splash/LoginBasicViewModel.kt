package com.challenge.demodaggerhilt.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.model.User
import com.challenge.demodaggerhilt.repository.di.IoDispatcher
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.usecases.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginBasicViewModel(
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO): BaseViewModel(ioDispatcher){


    val successUserLiveData        : LiveData<Boolean> get()   = _successUserLiveData
    private val _successUserLiveData    = MutableLiveData<Boolean>()


    fun getUser(email : String, password : String){
        executeSuspendNotProgress{
            _successUserLiveData.postValue(email.isNotEmpty() && password.isNotEmpty())
        }
    }
}