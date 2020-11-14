package com.pavelrukin.reddit.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.pavelrukin.reddit.api.RedditApi
import com.pavelrukin.reddit.db.RedditDatabase
import com.pavelrukin.reddit.model.RedditKeys
import com.pavelrukin.reddit.model.TopPostResponse
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RedditRemoteMediator(
    private val redditService: RedditApi,
    private val redditDatabase: RedditDatabase
) : RemoteMediator<Int, TopPostResponse.DataTop.Children.DataTopItem>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopPostResponse.DataTop.Children.DataTopItem>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND ->{
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getRedditKeys()
                }
            }
            val response = redditService.getTopRedditPosts(
                loadSize = 40,
                after = loadKey?.after,
                before = loadKey?.before
            )
            val listing = response.body()?.dataTop
            val redditPosts = listing?.children?.map { it.dataTopItem }
            if (redditPosts != null) {
                redditDatabase.withTransaction {
                    redditDatabase.redditKeysDao().saveRedditKeys(RedditKeys(0, listing.after, listing.before))
                    redditDatabase.redditPostsDao().savePosts(redditPosts)
                }

            }
            MediatorResult.Success(endOfPaginationReached = listing?.after == null)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }

    }

    private suspend fun getRedditKeys(): RedditKeys? {
        return redditDatabase.redditKeysDao().getRedditKeys().firstOrNull()

    }
}