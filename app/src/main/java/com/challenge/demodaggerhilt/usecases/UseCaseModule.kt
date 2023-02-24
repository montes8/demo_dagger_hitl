package com.challenge.demodaggerhilt.usecases

import org.koin.dsl.module

val useCaseModule = module {
    single { DataKoinUseCase(get()) }
}