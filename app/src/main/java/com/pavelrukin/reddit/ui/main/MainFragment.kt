package com.pavelrukin.reddit.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pavelrukin.reddit.R
import com.pavelrukin.reddit.databinding.MainFragmentBinding
import com.pavelrukin.reddit.model.TopPostResponse
import com.pavelrukin.reddit.ui.adapter.RedditAdapter
import com.pavelrukin.reddit.ui.adapter.RedditLoadingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    var redditAdapter: RedditAdapter = RedditAdapter(::onThumbnailClickListener)




    val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchPosts()
        initView()


    }
    private fun onThumbnailClickListener(dataTopItem: TopPostResponse.DataTop.Children.DataTopItem) {
        val bundle = Bundle().apply {
            putParcelable("thumb",dataTopItem)
        }
        findNavController().navigate(
            R.id.action_mainFragment_to_imageFragment,
            bundle
        )

    }
    private fun fetchPosts() {
        lifecycleScope.launch {
            viewModel.fetchPosts().collectLatest { pagingData ->
                redditAdapter.submitData(pagingData)
            }
        }

    }

    fun initView() {
        redditAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.rvMain.adapter = redditAdapter
        binding.rvMain.adapter = redditAdapter.withLoadStateHeaderAndFooter(
            header = RedditLoadingAdapter { redditAdapter.retry() },
            footer = RedditLoadingAdapter { redditAdapter.retry() }
        )
    }
}

