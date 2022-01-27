package com.android.newsfeed.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.newsfeed.R
import com.android.newsfeed.data.util.Resource
import com.android.newsfeed.databinding.ActivityMainBinding
import com.android.newsfeed.presentation.adapter.FeedAdapter
import com.android.newsfeed.presentation.vm.MainViewModel
import com.android.newsfeed.presentation.vm.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var adapter: FeedAdapter

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.fragment) as NavHostFragment
//        val navController = navHostFragment.navController

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }
}