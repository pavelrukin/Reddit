package com.pavelrukin.reddit.di


import com.pavelrukin.reddit.ui.image.ImageViewModel
import com.pavelrukin.reddit.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel { ImageViewModel(get()) }
}