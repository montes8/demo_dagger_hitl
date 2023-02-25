package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.adapter.getResultOrThrowException
import javax.inject.Inject

class DataHiltNetwork @Inject constructor(private var apiService: ServiceApi){
      suspend fun getList(): List<String> {
          val response = apiService.getListHilt()
          return response.getResultOrThrowException()
    }
}
