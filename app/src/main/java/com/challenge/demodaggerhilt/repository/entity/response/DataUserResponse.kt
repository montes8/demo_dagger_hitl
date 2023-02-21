package com.challenge.demodaggerhilt.repository.entity.response

import com.challenge.demodaggerhilt.repository.entity.response.UserResponse
import com.google.gson.annotations.SerializedName

class DataUserResponse(
    @SerializedName("token")
    var token :String,
    @SerializedName("usuario")
    var user : UserResponse
){
    fun toLogin() = Pair(user.toUser(),token)

    fun toUser() = user.toUser()
}