package com.android.newsfeed.presentation.di

import com.android.newsfeed.data.api.FeedAPIService
import com.android.newsfeed.data.repository.dataSource.FeedRemoteDataSource
import com.android.newsfeed.data.repository.dataSourceImpl.FeedRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideFeedRemoteDataSource(
        feedAPIService: FeedAPIService
    ): FeedRemoteDataSource {
        return FeedRemoteDataSourceImpl(feedAPIService)
    }

}