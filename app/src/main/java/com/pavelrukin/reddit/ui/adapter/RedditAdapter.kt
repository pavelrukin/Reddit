package com.pavelrukin.reddit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pavelrukin.reddit.R
import com.pavelrukin.reddit.model.TopPostResponse
import com.pavelrukin.reddit.utils.DiffUtilCallBack
import kotlinx.android.synthetic.main.item_main_adapter.view.*

class RedditAdapter :
    PagingDataAdapter<TopPostResponse.DataTop.Children.DataTopItem, RedditAdapter.RedditViewHolder>(
        DiffUtilCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_adapter, parent, false)
        return RedditViewHolder(view)
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class RedditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val authorText: TextView = itemView.tv_author
        private val commentsText: TextView = itemView.tv_num_comments
        private val createdText: TextView = itemView.tv_created

        fun bindPost(redditPost: TopPostResponse.DataTop.Children.DataTopItem) {
            with(redditPost) {
                authorText.text = author
                commentsText.text = numComments.toString()
                createdText.text = created.toString()
            }
        }
    }
}