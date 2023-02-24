package com.challenge.demodaggerhilt.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.usecases.DataKoinUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


class LoginThreeViewModel(private val appUseCase: DataKoinUseCase,private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO): BaseViewModel(ioDispatcher){

   // private val appUseCase: DataKoinUseCase by inject()

    val successListLiveData        : LiveData<List<String>> get()   = _successListLiveData
    private val _successListLiveData    = MutableLiveData<List<String>>()


    fun getList(){
        executeSuspendNotProgress{
            val response = appUseCase.getList()
            _successListLiveData.postValue(response)
        }
    }
}