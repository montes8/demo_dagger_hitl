package com.challenge.demodaggerhilt.usecases


class DataKoinUseCase(private val iAuthRepositoryNetwork: IAppRepositoryNetwork) {

    //  private val iAuthRepositoryNetwork: DataKoinNetwork by inject()

      suspend fun getList(): List<String> = iAuthRepositoryNetwork.getList()
}