package com.challenge.demodaggerhilt.repository.api

import com.challenge.demodaggerhilt.model.User
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.adapter.getResultOrThrowException
import com.challenge.demodaggerhilt.repository.entity.response.UserResponse
import javax.inject.Inject

class AppNetwork @Inject constructor(private val serviceApi : ServiceApi){

    suspend fun getUser(email:String,pass:String): User {
        val response = serviceApi.loginGeneric(UserResponse("gabbi0812@gmail.com","gabbi@1"))
        val result = response.getResultOrThrowException()
        return result.toUser()
    }

    suspend fun getUserTwo(): Boolean {
        return true }

}