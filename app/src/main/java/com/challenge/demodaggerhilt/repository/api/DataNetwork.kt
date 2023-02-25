package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response
import javax.inject.Inject

open class DataNetwork
constructor(private val serviceApi: ServiceApi) : ListenerLocalDataSource{

     override fun getList(): Flow<List<String>> { return flowOf(testList) }
     override  suspend fun getListService(): Response<List<String>> {
       return serviceApi.getList()
    }
}


interface ListenerLocalDataSource {
    fun getList(): Flow<List<String>>

    suspend fun getListService(): Response<List<String>>
}