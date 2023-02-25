package com.challenge.demodaggerhilt.repository.di

import android.content.Context
import android.content.SharedPreferences
import com.challenge.demodaggerhilt.BuildConfig
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import com.challenge.demodaggerhilt.usecases.IAppRepositoryNetwork
import com.challenge.demodaggerhilt.utils.AUTHORIZATION
import com.challenge.demodaggerhilt.utils.CONTENT_TYPE
import com.challenge.demodaggerhilt.utils.NAME_BASE_URL
import com.challenge.demodaggerhilt.utils.TIMEOUT
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { providerHttpLoggingInterceptor() }
    single { providerCache(get()) }
    single { ApiInterceptor() }
    single { providerOkHttpClient(get(), get()) }
    single { providerRetrofit(getProperty(NAME_BASE_URL), get()) }
    single { providerApi(get()) }
    single <IAppRepositoryNetwork>{ DataKoinNetwork(get()) }

}

fun providerApi(retrofit: Retrofit): ServiceApi {
    return retrofit.create(ServiceApi::class.java)
}

fun providerRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiInterceptor: ApiInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiInterceptor)
        .build()
}

fun providerCache(context: Context): Cache {
    val cacheSize: Long = 10485760
    return Cache(context.cacheDir, cacheSize)
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
            .addHeader("contenttype", CONTENT_TYPE)
        request = builder.build()
        return chain.proceed(request)
    }
}

