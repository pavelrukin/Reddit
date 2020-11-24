package com.pavelrukin.reddit.di


import com.pavelrukin.reddit.repository.RedditRepository
import com.pavelrukin.reddit.utils.FileUtils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single {
        RedditRepository(get(),get() )
    }
    single { FileUtils(androidContext()) }
}
