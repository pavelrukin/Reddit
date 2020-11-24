package com.pavelrukin.reddit.repository

import androidx.paging.PagingData
import com.pavelrukin.reddit.model.TopPostResponse
import kotlinx.coroutines.flow.Flow

interface IRedditRepository {
   suspend fun fetchPosts(): Flow<PagingData<TopPostResponse.DataTop.Children.DataTopItem>>
}
