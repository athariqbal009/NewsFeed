package com.android.newsfeed.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.newsfeed.R
import com.android.newsfeed.data.util.Resource
import com.android.newsfeed.databinding.FragmentFeedBinding
import com.android.newsfeed.presentation.adapter.FeedAdapter
import com.android.newsfeed.presentation.vm.MainViewModel
import timber.log.Timber

class FeedFragment : Fragment() {
    private lateinit var fragmentFeedBinding : FragmentFeedBinding
    private lateinit var viewModel: MainViewModel
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
        viewModel = (activity as MainActivity).viewModel
        feedAdapter = (activity as MainActivity).adapter
        initRecyclerView()
        viewFeedList()
    }

    private fun initRecyclerView() {
        fragmentFeedBinding.recyclerView.apply {
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun viewFeedList() {
        viewModel.uiState.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Loading-> {

                }
                is Resource.Success-> {
                    Timber.d(response.data.toString())
                    response.data?.let {
                        feedAdapter.differ.submitList(it.rows)
                    }
                }
                is Resource.Error-> {

                }
            }
        })
    }
}