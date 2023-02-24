package com.challenge.demodaggerhilt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.challenge.demodaggerhilt.repository.di.IoDispatcher
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.usecases.DataHiltUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeTwoViewModel @Inject constructor(private val appUseCase: DataHiltUseCase,@IoDispatcher
private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO): BaseViewModel(){


    val successListLiveData        : LiveData<List<String>> get()   = _successListLiveData
    private val _successListLiveData    = MutableLiveData<List<String>>()


    fun getList(){
        viewModelScope.launch(ioDispatcher) {
            val response = appUseCase.getList()
            _successListLiveData.postValue(response)
        }
    }
}