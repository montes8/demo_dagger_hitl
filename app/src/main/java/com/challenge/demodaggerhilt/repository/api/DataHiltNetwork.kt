package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.utils.testList
import javax.inject.Inject

class DataHiltNetwork @Inject constructor(){

      suspend fun getList(): List<String> {
        return testList
    }

}
