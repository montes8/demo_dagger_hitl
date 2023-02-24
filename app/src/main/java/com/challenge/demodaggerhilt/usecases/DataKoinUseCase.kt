package com.challenge.demodaggerhilt.usecases

import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject

class DataKoinUseCase(private val iAuthRepositoryNetwork: IAppRepositoryNetwork) {

    //  private val iAuthRepositoryNetwork: DataKoinNetwork by inject()

      suspend fun getList(): List<String> = iAuthRepositoryNetwork.getList()
}