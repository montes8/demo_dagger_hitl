package com.challenge.demodaggerhilt

import android.content.Context

open class BaseNetwork(private val context: Context) {
    suspend fun <T> executeWithConnection(block: suspend () -> T): T {
        if (!context.isConnected() || context.isAirplaneModeActive()) {
            throw NetworkException()
        }
        return block()
    }

}