package com.pavelrukin.reddit.repository

import androidx.paging.PagingSource
import com.pavelrukin.reddit.api.RedditApi
import com.pavelrukin.reddit.model.TopPostResponse
import retrofit2.HttpException
import java.io.IOException

class RedditPagingSource(private val redditService: RedditApi) :
    PagingSource<String, TopPostResponse.DataTop.Children.DataTopItem>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, TopPostResponse.DataTop.Children.DataTopItem> {
        return try {
            val response = redditService.getTopRedditPosts(loadSize = params.loadSize)
            val listing = response.body()?.dataTop
            val redditPosts = listing?.children?.map { it.dataTopItem }
            LoadResult.Page(
                redditPosts ?: listOf(),
                listing?.before,
                listing?.after
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean = true
}
