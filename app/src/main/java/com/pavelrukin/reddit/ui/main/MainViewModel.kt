package com.pavelrukin.reddit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pavelrukin.reddit.model.TopPostResponse
import com.pavelrukin.reddit.repository.RedditRepository
import com.pavelrukin.reddit.utils.Resource
import kotlinx.coroutines.flow.Flow

class MainViewModel(val repository: RedditRepository) : ViewModel() {



    fun fetchPosts(): Flow<PagingData<TopPostResponse.DataTop.Children.DataTopItem>> {

        return repository.fetchPosts().cachedIn(viewModelScope)
    }

    // val concertList: LiveData<PagedList<Concert>> =
    //            concertDao.concertsByDate().toLiveData(pageSize = 50)
}