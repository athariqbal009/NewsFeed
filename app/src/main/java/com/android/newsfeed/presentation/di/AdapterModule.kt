package com.android.newsfeed.presentation.di

import com.android.newsfeed.presentation.adapter.FeedAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideFeedAdapter(): FeedAdapter {
        return FeedAdapter()
    }
}