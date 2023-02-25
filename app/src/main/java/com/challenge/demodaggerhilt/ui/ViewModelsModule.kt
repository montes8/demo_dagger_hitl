package com.challenge.demodaggerhilt.ui


import com.challenge.demodaggerhilt.ui.list_koin.ListKoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ListKoinViewModel(get()) }
}
