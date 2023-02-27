package com.challenge.demodaggerhilt.usecases

import com.challenge.demodaggerhilt.repository.api.ListenerLocalDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class DataUseCase (private val iAppRepositoryNetwork: ListenerLocalDataSource) {

      fun getList(): Flow<List<String>> = iAppRepositoryNetwork.getList()


      suspend fun getListService(): Response<List<String>> = iAppRepositoryNetwork.getListService()

}