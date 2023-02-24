package com.challenge.demodaggerhilt.ui

import com.challenge.demodaggerhilt.ui.home.HomeThreeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeThreeViewModel() }
}