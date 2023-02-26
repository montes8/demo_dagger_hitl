package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.repository.GenericResponse
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.adapter.getResultOrThrowException
import com.challenge.demodaggerhilt.usecases.IAppRepositoryNetwork

class DataKoinNetwork(var apiService: ServiceApi) : IAppRepositoryNetwork {

    suspend fun getListService() = apiService.getListKoin()

    override suspend fun getList(): List<String> {
        val response = apiService.getListKoin()
        return response.getResultOrThrowException()
    }
}
