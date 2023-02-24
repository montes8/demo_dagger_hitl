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
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appUseCase: AppUseCase,
                                       @IoDispatcher
                                       private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO): BaseViewModel(ioDispatcher){


    val successUserLiveData        : LiveData<User> get()   = _successUserLiveData
    private val _successUserLiveData    = MutableLiveData<User>()

    val successUserTwoLiveData        : LiveData<Boolean> get()   = _successUserTwoLiveData
    private val _successUserTwoLiveData    = MutableLiveData<Boolean>()

    fun getUser(email:String,pass:String){
            executeSuspendNotProgress {
                val response = appUseCase.getUser(email,pass)
                _successUserLiveData.postValue(response)
            }
    }

    fun getUserTwo(email : String, password : String){
        executeSuspendNotProgress{
            val response = appUseCase.getUserTwo()
            _successUserTwoLiveData.postValue(response)
        }
    }


}