package com.challenge.demodaggerhilt.ui


import com.challenge.demodaggerhilt.ui.splash.LoginThreeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { LoginThreeViewModel(get()) }
}
