package com.pavelrukin.reddit.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pavelrukin.reddit.model.TopPostResponse

@Dao
interface RedditPostsDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePosts(redditPosts: List<TopPostResponse.DataTop.Children.DataTopItem>)

    @Query("SELECT * FROM redditPosts")
    fun getPosts(): PagingSource<Int, TopPostResponse.DataTop.Children.DataTopItem>

}