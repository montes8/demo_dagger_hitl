package com.challenge.demodaggerhilt.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.challenge.demodaggerhilt.repository.api.ListenerLocalDataSource
import com.challenge.demodaggerhilt.repository.entity.response.CompleteErrorModel
import com.challenge.demodaggerhilt.repository.exception.UnAuthorizedException
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.ResponseBody
import org.w3c.dom.Text
import retrofit2.Response
import java.util.regex.Pattern


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

class FakeDataNetwork(
): ListenerLocalDataSource {

    override fun getList(): Flow<List<String>> {
        return flowOf(testList)
    }

    override suspend fun getListService(): Response<List<String>> {
        TODO("Not yet implemented")
    }
}

fun String.isEmailValid(): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(this).matches()
    }


fun String.lengthPlus1(): Int {
    return this.length + 1
}



fun TextView.textCustom(value: String):String{
     this.setText(value)
    return this.text.toString()
}

