package com.challenge.demodaggerhilt.repository.entity.response

import com.challenge.demodaggerhilt.repository.exception.ApiException
import com.challenge.demodaggerhilt.utils.defaultCode
import com.challenge.demodaggerhilt.utils.generalErrorMessage
import com.google.gson.annotations.SerializedName

data class CompleteErrorModel(
    @SerializedName("errorCode")
    var code: Int? = defaultCode,
    @SerializedName("title")
    val title: String? = "Error General",
    @SerializedName("description")
    val description: String?= generalErrorMessage
) : Exception(description){
    fun getException(): Exception {
        return CompleteErrorModel( this.code, this.title,this.description)
    }

    fun getApiException(): Exception {
        return ApiException( this.code?:0, this.title?: generalErrorMessage,this.description?: generalErrorMessage)
    }
}