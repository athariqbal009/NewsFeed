package com.android.newsfeed.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.android.newsfeed.BR
import com.android.newsfeed.R
import com.android.newsfeed.databinding.FragmentDetailBinding
import com.android.newsfeed.databinding.FragmentFeedBinding
import com.android.newsfeed.presentation.vm.MainViewModel
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private lateinit var fragmentDetailBinding: FragmentDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDetailBinding = FragmentDetailBinding.bind(view)

        val args : DetailFragmentArgs by navArgs()
        val feed = args.selectedFeed
        fragmentDetailBinding.model = feed
        (activity as MainActivity).supportActionBar?.title = feed.title
//        fragmentDetailBinding.textViewDescription.text = feed.description
//        fragmentDetailBinding.imageView
//        Glide.with(this).load(feed.imageHref).into(fragmentDetailBinding.imageView)
    }
}