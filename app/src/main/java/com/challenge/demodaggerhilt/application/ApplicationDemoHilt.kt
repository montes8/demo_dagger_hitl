package com.challenge.demodaggerhilt.application

import android.app.Application
import com.challenge.demodaggerhilt.BuildConfig
import com.challenge.demodaggerhilt.repository.di.networkModule
import com.challenge.demodaggerhilt.ui.viewModelsModule
import com.challenge.demodaggerhilt.usecases.useCaseModule
import com.challenge.demodaggerhilt.utils.NAME_BASE_URL
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@HiltAndroidApp
class ApplicationDemoHilt : Application() {

  override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ApplicationDemoHilt)
            modules(listOf(viewModelsModule,useCaseModule,networkModule))
            getKoin().setProperty(NAME_BASE_URL, BuildConfig.BASE_URL)

        }
    }
}