package com.pavelrukin.reddit.di


import com.pavelrukin.reddit.repository.RedditRepository

import org.koin.dsl.module

val repositoryModule = module {
    single {
        RedditRepository(get(),get() )
    }
}
