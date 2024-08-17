package com.android.newsfeed.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.newsfeed.R
import com.android.newsfeed.data.util.Resource
import com.android.newsfeed.databinding.FragmentFeedBinding
import com.android.newsfeed.presentation.adapter.FeedAdapter
import com.android.newsfeed.presentation.vm.MainViewModel
import timber.log.Timber

class FeedFragment : Fragment() {
    private lateinit var fragmentFeedBinding: FragmentFeedBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentFeedBinding = FragmentFeedBinding.bind(view)

        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        feedAdapter = (activity as MainActivity).adapter
        feedAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_feed", it)
            }
            findNavController().navigate(
                R.id.action_feedFragment_to_detailFragment,
                bundle
            )
        }
        initRecyclerView()
        viewFeedList()
        fragmentFeedBinding.swipeRefresh.setOnRefreshListener {
            viewFeedList()
        }
    }

    private fun initRecyclerView() {
        fragmentFeedBinding.recyclerView.apply {
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun viewFeedList() {
        fragmentFeedBinding.swipeRefresh.isRefreshing = true
        viewModel.getFeeds()
        viewModel.uiState.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    Timber.d(response.data.toString())
                    response.data?.let {
                        feedAdapter.differ.submitList(it.rows)
                    }
                }

                is Resource.Error -> {

                }
            }
        })
        fragmentFeedBinding.swipeRefresh.isRefreshing = false
    }
}