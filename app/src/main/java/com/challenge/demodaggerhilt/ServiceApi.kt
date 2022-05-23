package com.challenge.demodaggerhilt

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.*
typealias GenericResponse<T> = MapperResponse<BaseResponseV2<T>>

data class BaseResponseV2<T>(
    @SerializedName("payload")
    val payload: T
)

interface ServiceApi {

    @POST("api/auth/login")
    suspend fun login(@Body userResponse: UserResponse): Response<DataUserResponse>
}