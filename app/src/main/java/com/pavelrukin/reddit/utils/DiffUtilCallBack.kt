package com.pavelrukin.reddit.utils

import androidx.recyclerview.widget.DiffUtil
import com.pavelrukin.reddit.model.TopPostResponse

class DiffUtilCallBack : DiffUtil.ItemCallback<TopPostResponse.DataTop.Children.DataTopItem>() {
    override fun areItemsTheSame(oldItem: TopPostResponse.DataTop.Children.DataTopItem, newItem: TopPostResponse.DataTop.Children.DataTopItem): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: TopPostResponse.DataTop.Children.DataTopItem, newItem: TopPostResponse.DataTop.Children.DataTopItem): Boolean {
        return oldItem.key == newItem.key
                && oldItem.author == newItem.author
                && oldItem.created == newItem.created
                && oldItem.numComments == newItem.numComments
                && oldItem.thumbnail == newItem.thumbnail
    }
}