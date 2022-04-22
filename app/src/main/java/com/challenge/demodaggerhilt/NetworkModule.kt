package com.challenge.demodaggerhilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/movie/"


    @Provides
    @Singleton
    fun providerOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor,context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ServiceApi::class.java)


    @Provides
    @Singleton
    fun provideApiHelper(iAppRepositoryNetwork: IAppRepositoryNetwork): IAppRepositoryNetwork = iAppRepositoryNetwork


}

class ApiInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
            .addHeader("Content-Type", CONTENT_TYPE)
            //.header("x-os", PLATFORM)
            .addHeader("x-density", getDensity(context).toString())
            .addHeader("x-width", getWidth(context).toString())
            .addHeader("x-height", getHeight(context).toString())

        request = builder.build()
        return chain.proceed(request)
    }
}