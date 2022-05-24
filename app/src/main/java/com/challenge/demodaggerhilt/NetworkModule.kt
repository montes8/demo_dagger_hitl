package com.challenge.demodaggerhilt

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
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
        return ApiInterceptor(appContext)

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

class ApiInterceptor@Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
            .addHeader("Content-Type", CONTENT_TYPE)
            .addHeader(DEVICE_MODEL, "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}")
            .addHeader("x-density", getDensity(context).toString())
            .addHeader("x-width", getWidth(context).toString())
            .addHeader("x-height", getHeight(context).toString())
            //.header("x-os", PLATFORM)

        request = builder.build()
        return chain.proceed(request)
    }
}
