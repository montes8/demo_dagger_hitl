package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.utils.testList

class DataKoinNetwork {

      suspend fun getList(): List<String>{
        return testList
    }
}
