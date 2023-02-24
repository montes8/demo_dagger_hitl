package com.challenge.demodaggerhilt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.usecases.DataUseCase
import kotlinx.coroutines.flow.Flow


class HomeViewModel(private val appUseCase: DataUseCase): BaseViewModel(){


    val successListLiveData        : LiveData<List<String>> get()   = _successListLiveData
    private val _successListLiveData    = MutableLiveData<List<String>>()


    val list: Flow<List<String>> get() = appUseCase.getList()


}