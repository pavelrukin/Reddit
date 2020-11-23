package com.pavelrukin.reddit.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavelrukin.reddit.R
import com.pavelrukin.reddit.databinding.ItemMainAdapterBinding
import com.pavelrukin.reddit.model.TopPostResponse
import com.pavelrukin.reddit.utils.extensions.timeAgo
import com.squareup.picasso.Picasso

class RedditAdapter :
    PagingDataAdapter<TopPostResponse.DataTop.Children.DataTopItem, RedditAdapter.RedditViewHolder>(
        DiffUtilCallBack()
    ) {

    val TAG = "REDDIT_ADAPTER"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        val binding = ItemMainAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RedditViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class RedditViewHolder(private val binding: ItemMainAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("LogNotTimber")
        fun bindPost(redditPost: TopPostResponse.DataTop.Children.DataTopItem) {
            with(redditPost) {
                binding.apply {
                    tvAuthor.text = author
                    tvNumComments.text = numComments.toString()
                    tvCreated.text = timeAgo(created.toInt())
                    Picasso.get().load(thumbnail).error(R.drawable.ic_image_not_supported).placeholder(R.drawable.ic_image_not_supported).into(ivRedditThumb)
                }
            }
        }
    }
}

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