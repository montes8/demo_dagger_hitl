package com.challenge.demodaggerhilt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.usecases.DataKoinUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject


class HomeThreeViewModel: BaseViewModel(), KoinComponent {


    private val appUseCase: DataKoinUseCase by inject()

    val successListLiveData        : LiveData<List<String>> get()   = _successListLiveData
    private val _successListLiveData    = MutableLiveData<List<String>>()


    fun getList(){
        executeSuspendNotProgress{
            val response = appUseCase.getList()
            _successListLiveData.postValue(response)
        }
    }
}