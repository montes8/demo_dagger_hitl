package com.challenge.demodaggerhilt.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.demodaggerhilt.model.User
import com.challenge.demodaggerhilt.ui.BaseViewModel
import com.challenge.demodaggerhilt.usecases.AppUseCase
import com.challenge.demodaggerhilt.usecases.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeViewModel(private val appUseCase: DataUseCase): BaseViewModel(){


    val successListLiveData        : LiveData<List<String>> get()   = _successListLiveData
    private val _successListLiveData    = MutableLiveData<List<String>>()


    val list: Flow<List<String>> get() = appUseCase.getList()


}