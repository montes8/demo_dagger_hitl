package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DataHiltNetwork @Inject constructor(){

      suspend fun getList(): List<String> {
        return testList
    }

}
