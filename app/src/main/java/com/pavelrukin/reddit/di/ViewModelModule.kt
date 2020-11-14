package com.pavelrukin.reddit.di


import com.pavelrukin.reddit.ui.main.MainViewModel
import com.pavelrukin.reddit.RedditApp
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}