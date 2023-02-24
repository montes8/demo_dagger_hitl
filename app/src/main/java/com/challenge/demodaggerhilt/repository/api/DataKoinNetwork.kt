package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.usecases.IAppRepositoryNetwork
import com.challenge.demodaggerhilt.utils.testList

class DataKoinNetwork: IAppRepositoryNetwork {

      override suspend fun getList(): List<String> {
        return testList
    }
}
