package com.challenge.demodaggerhilt

import retrofit2.Response
import retrofit2.http.*

interface ServiceApi {

    @GET("api/user/prueba")
    suspend fun getUser(): Response<User>
}