package com.challenge.demodaggerhilt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.usecases.DataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ListViewModel(private val appUseCase: DataUseCase,private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO): BaseViewModel(ioDispatcher){


    val successListLiveData        : LiveData<List<String>> get()   = _successListLiveData
    private val _successListLiveData    = MutableLiveData<List<String>>()

    val successListTwoLiveData        : LiveData<Response<List<String>>> get()   = _successListTwoLiveData
    private val _successListTwoLiveData    = MutableLiveData<Response<List<String>>>()


    val list: Flow<List<String>> get() = appUseCase.getList()

    fun getListService(){
        executeSuspendNotProgress{
            val response = appUseCase.getListService()
            if (response.isSuccessful) {
                _successListTwoLiveData.postValue(response)
            } else {
                _successListTwoLiveData.postValue(null)
            }

        }
    }


    fun getListServiceTwo(){
        executeSuspendNotProgress{
            val response = appUseCase.getListService()
            if (response.isSuccessful) {
                _successListLiveData.postValue(response.body())
            } else {
                _successListLiveData.postValue(null)
            }

        }
    }

}