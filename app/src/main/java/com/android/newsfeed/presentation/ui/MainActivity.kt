package com.android.newsfeed.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.newsfeed.R
import com.android.newsfeed.databinding.ActivityMainBinding
import com.android.newsfeed.presentation.adapter.FeedAdapter
import com.android.newsfeed.presentation.vm.MainViewModel
import com.android.newsfeed.presentation.vm.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var adapter: FeedAdapter

    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener {controller, destination, arguments ->

        }

        //viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }
}