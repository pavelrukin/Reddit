package com.pavelrukin.reddit.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pavelrukin.reddit.model.TopPostResponse
import com.pavelrukin.reddit.repository.RedditRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(val repository: RedditRepository) : ViewModel() {



  suspend  fun fetchPosts(): Flow<PagingData<TopPostResponse.DataTop.Children.DataTopItem>> {

        return repository.fetchPosts().cachedIn(viewModelScope)
    }


}