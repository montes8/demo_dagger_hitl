package com.challenge.demodaggerhilt.repository.di

import android.content.Context
import com.challenge.demodaggerhilt.BuildConfig
import com.challenge.demodaggerhilt.application.ApplicationDemoHilt
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.adapter.CoroutinesResponseCallAdapterFactory
import com.challenge.demodaggerhilt.utils.*
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG)HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Singleton
    @Provides
    fun providerOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,apiInterceptor : Interceptor,@ApplicationContext appContext: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .addInterceptor(ChuckInterceptor(appContext))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,baseUrl: String
    ): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(@ApplicationContext appContext: Context): Interceptor {
        return ApiInterceptor()

    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ServiceApi::class.java)

    @Singleton
    @Provides
    fun provideContext(application: ApplicationDemoHilt): Context {
        return application.applicationContext
    }

}

/*class ApiInterceptor@Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
            .addHeader("Content-Type", CONTENT_TYPE)
            .addHeader(DEVICE_MODEL, "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}")

        request = builder.build()
        return chain.proceed(request)
    }
}*/

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher


