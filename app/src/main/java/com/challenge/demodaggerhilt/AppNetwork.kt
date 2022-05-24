package com.challenge.demodaggerhilt


import android.util.Log
import javax.inject.Inject

class AppNetwork @Inject constructor(private val serviceApi : ServiceApi){

    suspend fun getUser(): User {
        Log.d("TAGUSER","getUserAppNetwork")
        val response = serviceApi.loginGeneric(UserResponse("gabbi@gmail.com","gabbi20"))
        val result = response.getResultOrThrowException()
        return result.toUser()

      /*  val response = serviceApi.login(UserResponse("gabbi@gmail.com","gabbi20"))
        var data : User? = null
        if (response.isSuccessful) {
            Log.d("TAGUSER","getUserisSuccessful")
            data = response.validateBody().toUser()
        }
        Log.d("TAGUSER","getUserisError")
        return data?: throw response.errorBody()?.toCompleteErrorModel(response.code())?.getException() ?: Exception()*/
    }

}