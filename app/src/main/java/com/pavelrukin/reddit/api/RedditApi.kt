package com.pavelrukin.reddit.api

import com.pavelrukin.reddit.model.TopPostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("top.json")
    suspend fun getTopRedditPosts(
        @Query("limit")
        loadSize: Int = 0,
        @Query("after")
        after: String? = null,
        @Query("before")
        before: String? = null

    ): Response<TopPostResponse>
}
