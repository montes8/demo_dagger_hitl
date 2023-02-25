package com.challenge.demodaggerhilt.usecases

import com.challenge.demodaggerhilt.repository.api.DataHiltNetwork
import retrofit2.Response
import javax.inject.Inject


class DataHiltUseCase @Inject constructor(private val iAppRepositoryNetwork: DataHiltNetwork) {

      suspend fun getList(): List<String> = iAppRepositoryNetwork.getList()
}