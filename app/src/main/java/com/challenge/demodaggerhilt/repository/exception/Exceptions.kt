package com.challenge.demodaggerhilt.repository.exception

import com.challenge.demodaggerhilt.utils.EMPTY

data class ApiException(val code: Int = 0,val title: String = EMPTY, val mMessage: String = EMPTY): Exception()

class GenericException() : Exception()

class NetworkException() : Exception()

class UnAuthorizedException() : Exception()
