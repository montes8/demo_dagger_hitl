package com.challenge.demodaggerhilt

import retrofit2.Response
import retrofit2.http.*
typealias GenericResponse<T> = MapperResponse<T>


interface ServiceApi {

    @POST("api/auth/login")
    suspend fun login(@Body userResponse: UserResponse): Response<DataUserResponse>

    @POST("api/auth/login")
    suspend fun loginGeneric(@Body userResponse: UserResponse): GenericResponse<DataUserResponse>
}