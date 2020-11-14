package com.pavelrukin.reddit.di

import android.app.Application
import androidx.room.Room
import com.pavelrukin.reddit.db.RedditDatabase
import com.pavelrukin.reddit.db.dao.RedditKeysDao
import com.pavelrukin.reddit.db.dao.RedditPostsDao
import com.pavelrukin.reddit.utils.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): RedditDatabase {
        return Room.databaseBuilder(application, RedditDatabase::class.java, Constants.DATABSE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideRedditKeyDao(database: RedditDatabase): RedditKeysDao {
        return  database.redditKeysDao()
    }
    fun provideRedditPostsDao(database: RedditDatabase): RedditPostsDao {
        return  database.redditPostsDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideRedditKeyDao(get()) }
    single{provideRedditPostsDao(get())}
}