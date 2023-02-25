package com.challenge.demodaggerhilt.ui.list_hilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.repository.di.IoDispatcher
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.usecases.DataHiltUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class ListHiltViewModel @Inject constructor(private val appUseCase: DataHiltUseCase, @IoDispatcher
private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO): BaseViewModel(ioDispatcher){


    val successListLiveData        : LiveData<List<String>> get()   = _successListLiveData
    private val _successListLiveData    = MutableLiveData<List<String>>()


    fun getList(){
        executeSuspendNotProgress{
            val response = appUseCase.getList()
            _successListLiveData.postValue(response)
        }
    }
}