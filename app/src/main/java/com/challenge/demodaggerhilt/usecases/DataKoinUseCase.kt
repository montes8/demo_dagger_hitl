package com.challenge.demodaggerhilt.usecases

import org.koin.core.KoinComponent
import org.koin.core.inject

class DataKoinUseCase : KoinComponent {

      private val iAuthRepositoryNetwork: IAppRepositoryNetwork by inject()

      suspend fun getList(): List<String> = iAuthRepositoryNetwork.getList()
}