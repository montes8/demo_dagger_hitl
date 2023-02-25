package com.challenge.demodaggerhilt.repository

import android.content.Context
import com.challenge.demodaggerhilt.repository.entity.response.DataUserResponse
import com.challenge.demodaggerhilt.repository.adapter.MapperResponse
import com.challenge.demodaggerhilt.repository.entity.response.UserResponse
import com.challenge.demodaggerhilt.utils.TIMEOUT
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

typealias GenericResponse<T> = MapperResponse<T>


interface ServiceApi {

    @POST("api/auth/login")
    suspend fun login(@Body userResponse: UserResponse): Response<DataUserResponse>

    @POST("api/auth/login")
    suspend fun loginGeneric(@Body userResponse: UserResponse): GenericResponse<DataUserResponse>


    @GET("api/user/listTest")
    suspend fun getListKoin(): GenericResponse<List<String>>

    @GET("api/user/listTest")
    suspend fun getListHilt(): GenericResponse<List<String>>

    @GET("api/user/listTest")
    suspend fun getList(): Response<List<String>>




    companion object {
        var retrofitService: ServiceApi? = null
        fun getInstance(appContext: Context) : ServiceApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://0977-2800-200-e3c0-1123-8d1f-8541-1981-610a.ngrok.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                        .addInterceptor(ChuckInterceptor(appContext)).build())
                    .build()
                retrofitService = retrofit.create(ServiceApi::class.java)
            }
            return retrofitService!!
        }

    }
}