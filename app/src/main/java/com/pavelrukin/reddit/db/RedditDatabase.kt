package com.pavelrukin.reddit.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pavelrukin.reddit.db.dao.RedditKeysDao
import com.pavelrukin.reddit.db.dao.RedditPostsDao
import com.pavelrukin.reddit.model.RedditKeys
import com.pavelrukin.reddit.model.TopPostResponse


@Database(
    entities = [TopPostResponse.DataTop.Children.DataTopItem::class, RedditKeys::class],
    version = 1,
    exportSchema = false
)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun redditPostsDao(): RedditPostsDao
    abstract fun redditKeysDao(): RedditKeysDao
}