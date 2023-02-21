package com.challenge.demodaggerhilt.repository

import android.content.Context
import com.challenge.demodaggerhilt.repository.exception.NetworkException
import com.challenge.demodaggerhilt.utils.isAirplaneModeActive
import com.challenge.demodaggerhilt.utils.isConnected

open class BaseNetwork(private val context: Context) {
    suspend fun <T> executeWithConnection(block: suspend () -> T): T {
        if (!context.isConnected() || context.isAirplaneModeActive()) {
            throw NetworkException()
        }
        return block()
    }

}