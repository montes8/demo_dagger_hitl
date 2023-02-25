package com.challenge.demodaggerhilt.usecases

import com.challenge.demodaggerhilt.repository.api.AppNetwork
import javax.inject.Inject


class AppUseCase @Inject constructor(private val iAppRepositoryNetwork: AppNetwork) {
     suspend fun getUser(email:String,pass:String) = iAppRepositoryNetwork.getUser(email,pass)
     suspend fun getUserTwo() = iAppRepositoryNetwork.getUserTwo()
     fun getUserThree() = true
}