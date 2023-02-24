package com.challenge.demodaggerhilt.repository.di

import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import com.challenge.demodaggerhilt.usecases.IAppRepositoryNetwork
import org.koin.dsl.module

val networkModule = module {
    single<IAppRepositoryNetwork> { DataKoinNetwork() }

}

