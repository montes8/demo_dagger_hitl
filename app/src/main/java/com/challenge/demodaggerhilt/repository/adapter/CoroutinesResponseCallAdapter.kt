package com.challenge.demodaggerhilt.repository.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiResponseCallAdapter<T : Any>(
    private val resultType: Type
) : CallAdapter<T, Call<MapperResponse<T>>> {

    override fun responseType(): Type = resultType
    override fun adapt(call: Call<T>): Call<MapperResponse<T>> {
        return CustomApiResponseCall(call, resultType)
    }
}