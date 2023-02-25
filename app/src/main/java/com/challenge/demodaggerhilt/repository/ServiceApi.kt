package com.challenge.demodaggerhilt.repository

import com.challenge.demodaggerhilt.repository.entity.response.DataUserResponse
import com.challenge.demodaggerhilt.repository.adapter.MapperResponse
import com.challenge.demodaggerhilt.repository.entity.response.UserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
typealias GenericResponse<T> = MapperResponse<T>


interface ServiceApi {

    @POST("api/auth/login")
    suspend fun login(@Body userResponse: UserResponse): Response<DataUserResponse>

    @POST("api/auth/login")
    suspend fun loginGeneric(@Body userResponse: UserResponse): GenericResponse<DataUserResponse>


    @GET("api/auth/login")
    suspend fun getListKoin(): GenericResponse<List<String>>

    @GET("api/auth/login")
    suspend fun getListHilt(): GenericResponse<List<String>>

    @GET("api/auth/login")
    suspend fun getList(): Response<List<String>>



    companion object {
        var retrofitService: ServiceApi? = null
        fun getInstance() : ServiceApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://0977-2800-200-e3c0-1123-8d1f-8541-1981-610a.ngrok.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ServiceApi::class.java)
            }
            return retrofitService!!
        }

    }
}