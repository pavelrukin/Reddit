package com.pavelrukin.reddit.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pavelrukin.reddit.api.RedditApi
import com.pavelrukin.reddit.db.RedditDatabase
import com.pavelrukin.reddit.model.TopPostResponse
import kotlinx.coroutines.flow.Flow

class RedditRepository(val redditService: RedditApi, val redditDatabase: RedditDatabase ) {


    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<TopPostResponse.DataTop.Children.DataTopItem>> {
        return Pager(
            PagingConfig(pageSize = 40, enablePlaceholders = false, prefetchDistance = 3),

            remoteMediator = RedditRemoteMediator(redditService, redditDatabase),
            pagingSourceFactory = { redditDatabase.redditPostsDao().getPosts() }
        ).flow
    }
}
