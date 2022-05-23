package com.challenge.demodaggerhilt

import javax.inject.Inject


class AppUseCase @Inject constructor(private val iAppRepositoryNetwork: AppNetwork) {
     suspend fun getUser() = iAppRepositoryNetwork.getUser()
}