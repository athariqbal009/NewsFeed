package com.android.newsfeed.presentation.di

import com.android.newsfeed.data.repository.FeedRepositoryImpl
import com.android.newsfeed.data.repository.dataSource.FeedRemoteDataSource
import com.android.newsfeed.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        feedRemoteDataSource: FeedRemoteDataSource
    ): FeedRepository {
        return FeedRepositoryImpl(
            feedRemoteDataSource
        )
    }
}