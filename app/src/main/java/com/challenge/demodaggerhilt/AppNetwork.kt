package com.challenge.demodaggerhilt

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

class AppNetwork @Inject constructor(private val api: ServiceApi,context: Context) : IAppRepositoryNetwork, BaseNetwork(context){

    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

}