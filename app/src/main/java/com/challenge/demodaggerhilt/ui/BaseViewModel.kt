package com.challenge.demodaggerhilt.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel(private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO) : ViewModel() {
    val errorLiveData = MutableLiveData<Throwable>()
    val progressLiveData = MutableLiveData<Boolean>()


    fun executeSuspendNotProgress(func: suspend () -> Unit) =
        viewModelScope.launch(ioDispatcher) {
            try {
                func()
            } catch (ex: Exception) {
                errorLiveData.postValue(ex)
            }
        }

    fun execute(func:() -> Unit) =
        viewModelScope.launch(ioDispatcher) {
            try {
                func()
            } catch (ex: Exception) {
                errorLiveData.postValue(ex)
            }
        }
}