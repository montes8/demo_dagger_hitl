package com.challenge.demodaggerhilt.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.util.Log
import com.challenge.demodaggerhilt.repository.entity.response.CompleteErrorModel
import com.challenge.demodaggerhilt.repository.exception.UnAuthorizedException
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

fun getDensity(context: Context): Float {

    return context.resources.displayMetrics.density
}

fun getWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun getHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}


fun Context?.isConnected(): Boolean {
    return this?.let {
        val cm = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability((NetworkCapabilities.NET_CAPABILITY_INTERNET)) ?: false
    } ?: false
}

fun Context?.isAirplaneModeActive(): Boolean {
    return this?.let {
        return Settings.Global.getInt(it.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    } ?: false
}


fun <T> Response<T>.validateBody() : T {
    this.body()?.let {
        return it
    } ?: throw NullPointerException()
}

fun ResponseBody?.toCompleteErrorModel(code : Int) : CompleteErrorModel? {
    return this?.let {
        Log.d("TAGUSER","toCompleteErrorModel")
        return  if (code == 407) throw UnAuthorizedException () else Gson().fromJson(it.string(), CompleteErrorModel::class.java)
    } ?: CompleteErrorModel()
}