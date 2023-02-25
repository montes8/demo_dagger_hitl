package com.challenge.demodaggerhilt.usecases

interface IAppRepositoryNetwork {

    suspend fun getList(): List<String>
}